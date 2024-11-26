package com.itachialy.moji_store.controller;

import com.itachialy.moji_store.model.Category;
import com.itachialy.moji_store.model.Product;
import com.itachialy.moji_store.service.ICategoryService;
import com.itachialy.moji_store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final IProductService productService;
    private final ICategoryService categoryService;

    @Autowired
    public AdminController(IProductService productService, ICategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // Hiển thị danh sách sản phẩm
    @GetMapping("")
    public String showList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/home_admin"; // Đường dẫn tới view danh sách sản phẩm
    }

    // Hiển thị form thêm sản phẩm
    @GetMapping("/products/create")
    public String showProductCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "product/create"; // Đường dẫn tới view thêm sản phẩm
    }

    // Xử lý thêm sản phẩm
    @PostMapping("/products/create")
    public String createProduct(
            @ModelAttribute Product product,
            @RequestParam("imageFile") MultipartFile imageFile
    ) {
        try {
            if (!imageFile.isEmpty()) {
                String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                Path uploadPath = Paths.get("uploads/images"); // Thư mục lưu ảnh
                Files.createDirectories(uploadPath); // Tạo thư mục nếu chưa có
                Files.copy(imageFile.getInputStream(), uploadPath.resolve(fileName));
                product.setImage(fileName); // Lưu tên file ảnh vào DB
            }
            productService.save(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin"; // Quay lại danh sách sản phẩm
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
                product.setImage(fileName);
            }
            productService.save(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/products";
    }

    // Xử lý xóa sản phẩm
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/admin/products";
    }
}
