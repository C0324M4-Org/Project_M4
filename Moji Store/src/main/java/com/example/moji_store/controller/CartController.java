package com.example.moji_store.controller;

import com.example.moji_store.model.Product;
import com.example.moji_store.service.CartService;
import com.example.moji_store.service.ProductService;
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

    private final String userId = "guest"; // Giả lập user ID

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cart", cartService.getCartByUser(userId));
        return "cart/view";
    }

    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, @RequestParam(defaultValue = "1") int quantity) {
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

    @PostMapping("/update/{itemId}")
    public String updateCartItem(@PathVariable Long itemId, @RequestParam int quantity) {
        cartService.updateCartItem(userId, itemId, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart(userId);
        return "redirect:/cart";
    }
}
