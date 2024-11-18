package com.example.moji.controller;

import com.example.moji.model.Product;
import com.example.moji.service.CartService;
import com.example.moji.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    private final String userId = "guest"; // Giả sử giỏ hàng theo user tạm (nâng cao: thêm user login)

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cart", cartService.getCartByUser(userId));
        return "cart/view";
    }

    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, @RequestParam int quantity) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            cartService.addItemToCart(userId, product, quantity);
        }
        return "redirect:/cart";
    }

    @GetMapping("/remove/{itemId}")
    public String removeFromCart(@PathVariable Long itemId) {
        cartService.removeItemFromCart(userId, itemId);
        return "redirect:/cart";
    }
}
