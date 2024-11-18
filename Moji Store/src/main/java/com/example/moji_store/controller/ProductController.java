package com.example.moji_store.controller;


import com.example.moji_store.dto.ProductDTO;
import com.example.moji_store.model.Product;
import com.example.moji_store.service.ICategoryService;
import com.example.moji_store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
=======
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
>>>>>>> 54eb4666dc03f63ecfa67d9b17e91979924cdc75

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class ProductController {
    private final IProductService iProductService;
    private final ICategoryService iCategoryService;

    @Autowired
    public ProductController(IProductService iProductService, ICategoryService iCategoryService) {
        this.iProductService = iProductService;
        this.iCategoryService = iCategoryService;
    }

    @GetMapping("")
    public String showList(Model model) {
        model.addAttribute("list", iProductService.findAll());
        return "/product/home_user";
    }

    @GetMapping("/show-create")
    public String showCreate(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("listC", iCategoryService.findAll());
        return "product/create";
    }

<<<<<<< HEAD
    @PostMapping("/create")
    public String createProduct(@ModelAttribute("productDTO") ProductDTO productDTO, Model model) {
        try {
            Product product = iProductService.convertToProduct(productDTO);
            iProductService.save(product);
            model.addAttribute("message", "Sản phẩm đã được thêm thành công!");
            return "redirect:/admin";
        } catch (IOException e) {
            model.addAttribute("imgError", "Lỗi khi tải ảnh: " + e.getMessage());
            model.addAttribute("productDTO", productDTO);
            model.addAttribute("listC", iCategoryService.findAll());
            return "product/create";
        } catch (Exception e) {
            // Giữ lại thông tin về lỗi lưu sản phẩm và hiển thị lại form
            model.addAttribute("message", "Có lỗi xảy ra khi lưu sản phẩm!");
            model.addAttribute("productDTO", productDTO); // Giữ lại thông tin sản phẩm đã nhập
            model.addAttribute("listC", iCategoryService.findAll()); // Giữ lại danh sách category
            return "product/create";
        }
=======
    @PostMapping("add")
    public String addProduct(@ModelAttribute("productC") Product product,
                             @RequestParam("image") MultipartFile image,
                             RedirectAttributes redirectAttributes) {
        try {
            // Lưu ảnh vào thư mục và lấy đường dẫn tương đối
            String imagePath = saveImage(image);
            product.setImage(imagePath); // Lưu tên file ảnh vào sản phẩm

            // Lưu sản phẩm vào cơ sở dữ liệu
            iProductService.save(product);
            redirectAttributes.addFlashAttribute("add", "Thêm mới thành công");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi tải ảnh lên!");
        }
        return "redirect:/";
    }

    private String saveImage(MultipartFile image) throws IOException {
        // Lấy tên file gốc
        String fileName = image.getOriginalFilename();
        // Đường dẫn lưu trữ
        String uploadDir = "src/main/resources/static/img/";

        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs(); // Tạo thư mục nếu chưa tồn tại
        }

        // Lưu file vào thư mục
        File file = new File(uploadDir + fileName);
        image.transferTo(file); // Chuyển file vào thư mục

        return "/img/" + fileName; // Trả về đường dẫn tương đối để lưu vào DB
>>>>>>> 54eb4666dc03f63ecfa67d9b17e91979924cdc75
    }
}
