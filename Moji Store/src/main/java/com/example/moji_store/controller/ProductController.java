package com.example.moji_store.controller;

import com.example.moji_store.model.Product;
import com.example.moji_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller

public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("")
    public String findAll(Model model) {
        List<Product> list = productService.findAllProducts();
        model.addAttribute("list",list);
        return "home";
    }

    @GetMapping("display-all-product")
    public String displayAll(Model model) {
        List<Product> list = productService.findAllProducts();
        model.addAttribute("list", list);
        return "display_all";
    }

    @GetMapping("view/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Optional<Product> optionalProduct = productService.findProductById(id);
        if (optionalProduct.isPresent()) {
            model.addAttribute("product", optionalProduct.get());
        } else {
            // Xử lý trường hợp không tìm thấy sản phẩm, ví dụ: chuyển hướng đến trang 404
            return "redirect:/404"; // Hoặc một trang thông báo nào đó
        }
        return "view_product"; // Trả về template cho trang view
    }






}
