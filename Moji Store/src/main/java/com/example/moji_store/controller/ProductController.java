package com.example.moji_store.controller;
import com.example.moji_store.model.Product;
import com.example.moji_store.service.ICategoryService;
import com.example.moji_store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICategoryService iCategoryService;



    @GetMapping("")
    public String showList(Model model){
        model.addAttribute("products", iProductService.findAll());
        return "product/home";
    }


    @GetMapping("show-create-form")
    public String showCreateForm(Model model){
        model.addAttribute("productC", new Product());
        model.addAttribute("listC", iCategoryService.findAll());
        return "product/create";
    }

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
    }



}
