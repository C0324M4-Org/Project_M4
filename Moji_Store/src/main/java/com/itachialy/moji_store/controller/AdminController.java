package com.itachialy.moji_store.controller;

import com.itachialy.moji_store.model.Image;
import com.itachialy.moji_store.model.Product;
import com.itachialy.moji_store.service.ICategoryService;
import com.itachialy.moji_store.service.IProductService;
import com.itachialy.moji_store.service.impl.ImageServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final IProductService productService;
    private final ICategoryService categoryService;
    private final ImageServiceImpl imageService;

    @Autowired
    public AdminController(IProductService productService, ICategoryService categoryService, ImageServiceImpl imageService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.imageService = imageService;
    }

    // Hiển thị danh sách sản phẩm
    @GetMapping("")
    public String showList(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "product/home_admin"; // Đường dẫn tới view danh sách sản phẩm
    }

    // Hiển thị form thêm sản phẩm
    @GetMapping("/products/create")
    public String showProductCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "product/create"; // Đường dẫn tới view thêm sản phẩm
    }

    @PostMapping("/products/create")
    public String addProduct(
            @Valid @ModelAttribute Product product,
            BindingResult result,
            @RequestParam("mainImage") MultipartFile mainImage,
            @RequestParam("productimages") MultipartFile[] imageList
    ) throws IOException {
        if (result.hasErrors()) {
            return "product/create"; // Trả về form nếu có lỗi
        }

        // Lưu ảnh chính
        if (!mainImage.isEmpty()) {
            try {
                String mainImageName = saveImageStatic(mainImage);
                product.setImage("/img/" + mainImageName);
            } catch (IOException e) {
                e.printStackTrace();
                return "product/create"; // Nếu lưu ảnh lỗi, trả về form
            }
        }

        // Thêm sản phẩm vào cơ sở dữ liệu
        productService.addProduct(product);

        // Lưu ảnh phụ
        for (MultipartFile image : imageList) {
            if (!image.isEmpty()) {
                try {
                    String imageUrl = saveImageStatic(image);
                    Image productImage = new Image();
                    productImage.setImagePath("/img/" + imageUrl);
                    productImage.setProduct(product);
                    imageService.addProductImage(productImage);
                } catch (IOException e) {
                    e.printStackTrace();
                    // Log lỗi lưu ảnh phụ, nhưng không ảnh hưởng tới luồng chính
                }
            }
        }

        return "redirect:/admin"; // Chuyển hướng về danh sách sản phẩm
    }

    // Hiển thị form chỉnh sửa sản phẩm
    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "product/edit"; // Đường dẫn tới view chỉnh sửa sản phẩm
    }

    // Xử lý chỉnh sửa sản phẩm
    @PostMapping("/products/edit/{id}")
    public String editProduct(
            @PathVariable Long id,
            @ModelAttribute Product product,
            @RequestParam("imageFile") MultipartFile imageFile
    ) {
        try {
            if (!imageFile.isEmpty()) {
                String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                Path uploadPath = Paths.get("uploads/images");
                Files.createDirectories(uploadPath);
                Files.copy(imageFile.getInputStream(), uploadPath.resolve(fileName));
                product.setImage("/img/" + fileName);
            }
            productService.save(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin";
    }

    // Xử lý xóa sản phẩm
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/admin";
    }

    private String saveImageStatic(MultipartFile image) throws IOException {
        Path dirImages = Paths.get("target/classes/static/img");
        if (!Files.exists(dirImages)) {
            Files.createDirectories(dirImages);
        }

        String newFileName = UUID.randomUUID() + "." + image.getOriginalFilename().split("\\.")[1];
        Path pathFileUpload = dirImages.resolve(newFileName);
        Files.copy(image.getInputStream(), pathFileUpload, StandardCopyOption.REPLACE_EXISTING);
        return newFileName;
    }
}
