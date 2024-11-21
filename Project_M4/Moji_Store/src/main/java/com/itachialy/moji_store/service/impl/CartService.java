package com.itachialy.moji_store.service.impl;

import com.itachialy.moji_store.model.Cart;
import com.itachialy.moji_store.model.CartItem;
import com.itachialy.moji_store.model.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {

    private final Map<String, Cart> carts = new HashMap<>();

    public Cart getCartByUser(String userId) {
        return carts.computeIfAbsent(userId, k -> new Cart(userId));
    }

    public void addItemToCart(String userId, Product product, int quantity) {
        Cart cart = getCartByUser(userId);
        cart.addItem(new CartItem(product, quantity));
    }

    public void updateCartItem(String userId, Long productId, int quantity) {
        Cart cart = getCartByUser(userId);
        cart.updateItemQuantity(productId, quantity);
    }

    public void removeItemFromCart(String userId, Long productId) {
        Cart cart = getCartByUser(userId);
        cart.removeItem(productId);
    }

    public void clearCart(String userId) {
        Cart cart = getCartByUser(userId);
        cart.clear();
    }

    public double getTotalPrice(String userId) {
        return getCartByUser(userId).getTotalPrice();
    }
}
