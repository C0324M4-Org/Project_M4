package com.itachialy.moji_store.controller;

import com.itachialy.moji_store.model.Cart;
import com.itachialy.moji_store.model.CartItem;
import com.itachialy.moji_store.model.Product;
import com.itachialy.moji_store.service.impl.CartService;
import com.itachialy.moji_store.service.impl.ProductServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductServiceImpl productService;

    @Autowired
    public CartController(CartService cartService, ProductServiceImpl productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    private final String userId = "guest"; // Giả lập user ID

    @GetMapping
    public String viewCart(Model model) {
        Cart cart = cartService.getCartByUser(userId);
        model.addAttribute("cartItems", cart.getItems());
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "product/cart";  // Chỉ định rõ ràng vị trí của cart.html
    }

    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, @RequestParam(defaultValue = "1") int quantity) {
        Product product = productService.getProductById(productId);
        if (product != null && quantity > 0) {
            cartService.addItemToCart(userId, product, quantity);  // Gọi đúng phương thức với Product và số lượng
        }
        return "redirect:/cart";
    }


    @PostMapping("/update")
    public String updateCart(@RequestParam Long productId, @RequestParam int quantity) {
        if (quantity > 0) {
            cartService.updateCartItem(userId, productId, quantity);
        }
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long productId) {
        cartService.removeItemFromCart(userId, productId);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart(userId);
        return "redirect:/cart";
    }
}

