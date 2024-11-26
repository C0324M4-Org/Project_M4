package com.itachialy.moji_store.controller;

import com.itachialy.moji_store.model.*;
import com.itachialy.moji_store.repository.AccountRepository;
import com.itachialy.moji_store.repository.OrderRepository;
import com.itachialy.moji_store.service.impl.CartService;
import com.itachialy.moji_store.service.impl.PaymentService; // Import PaymentService
import com.itachialy.moji_store.service.impl.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final AccountRepository accountRepository;
    private final OrderRepository orderRepository;
    private final PaymentService paymentService; // Declare PaymentService

    @Autowired
    public CartController(CartService cartService,
                          ProductService productService,
                          AccountRepository accountRepository,
                          OrderRepository orderRepository,
                          PaymentService paymentService) { // Inject PaymentService
        this.cartService = cartService;
        this.productService = productService;
        this.accountRepository = accountRepository;
        this.orderRepository = orderRepository;
        this.paymentService = paymentService; // Initialize PaymentService
    }

    private Account getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            return accountRepository.findByUsername(username);
        }
        return null;
    }

    @GetMapping
    public String viewCart(Model model) {
        Account currentUser = getCurrentUser();
        if (currentUser != null) {
            Cart cart = cartService.getCartByUser(currentUser);
            model.addAttribute("cartItems", (cart != null) ? cart.getItems() : new ArrayList<>());
            model.addAttribute("totalPrice", (cart != null) ? cart.getTotalPrice() : 0.0);
        } else {
            model.addAttribute("cartItems", new ArrayList<>());
            model.addAttribute("totalPrice", 0.0);
        }
        return "product/cart"; // Thymeleaf template for viewing the cart
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, @RequestParam(defaultValue = "1") int quantity) {
        Account currentUser = getCurrentUser();
        if (currentUser != null) {
            Product product = productService.getProductById(productId);
            if (product != null && quantity > 0) {
                cartService.addItemToCart(currentUser, product, quantity);
            }
        }
        return "redirect:/cart"; // Redirect to view cart after adding item
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam Long productId, @RequestParam int quantity) {
        Account currentUser = getCurrentUser();
        if (currentUser != null && quantity > 0) {
            cartService.updateCartItem(currentUser, productId, quantity);
        }
        return "redirect:/cart"; // Redirect to view cart after updating item
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long productId) {
        Account currentUser = getCurrentUser();
        if (currentUser != null) {
            cartService.removeItemFromCart(currentUser, productId);
        }
        return "redirect:/cart"; // Redirect to view cart after removing item
    }

    @GetMapping("/clear")
    public String clearCart() {
        Account currentUser = getCurrentUser();
        if (currentUser != null) {
            cartService.clearCart(currentUser);
        }
        return "redirect:/cart"; // Redirect to view cart after clearing it
    }

    @GetMapping("/checkout")
    public String processCheckout(@RequestParam String fullName,
                                  @RequestParam String phone,
                                  @RequestParam String address,
                                  @RequestParam(required = false) Boolean pickupOption,
                                  @RequestParam String paymentMethod,
                                  Model model) {

        Account currentUser = getCurrentUser();
        if (currentUser != null) {
            Cart cart = cartService.getCartByUser(currentUser);
            double totalPrice = cart.getTotalPrice();

            // Create a new order
            Order order = new Order(currentUser, fullName, phone, address, totalPrice);

            // Save the order to the database
            orderRepository.save(order);

            if ("VNPAY".equals(paymentMethod)) {
                String paymentUrl = paymentService.createPaymentUrl(totalPrice, order.getId().toString(), "http://localhost:8080/cart/payment-result");
                return "redirect:" + paymentUrl; // Redirect to VNPay payment page
            } else {
                return "redirect:/order/success"; // Redirect to success page after processing COD
            }
        } else {
            return "redirect:/login"; // Redirect to login if user is not authenticated
        }
    }
}