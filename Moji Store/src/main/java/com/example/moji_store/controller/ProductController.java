package com.example.moji_store.controller;

import com.example.moji_store.model.Product;
import com.example.moji_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    private List<Product> cart = new ArrayList<>(); // Giỏ hàng

    @GetMapping("")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products/list"; // Đường dẫn cũ
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/add";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "products/edit"; // Hiển thị form chỉnh sửa
        }
        return "redirect:/products";
    }

    @PostMapping("/{id}/update")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct != null) {
            product.setId(id); // Đảm bảo giữ nguyên ID
            productService.saveProduct(product);
        }
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/{id}/add-to-cart")
    public String addToCart(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            cart.add(product);
        }
        return "redirect:/products";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cart", cart);
        return "products/cart";
    }
}
