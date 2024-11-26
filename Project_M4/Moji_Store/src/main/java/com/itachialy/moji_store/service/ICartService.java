package com.itachialy.moji_store.service;

import com.itachialy.moji_store.model.Cart;
import com.itachialy.moji_store.model.CartItem;
import com.itachialy.moji_store.model.Product;

import java.util.List;

public interface ICartService {
    Cart getCartByUser(String userId);
    void addItemToCart(String userId, Product product, int quantity);
    void updateCartItem(String userId, Long productId, int quantity);
    void removeItemFromCart(String userId, Long productId);
    void clearCart(String userId);
    List<CartItem> getCartItems(String userId);
    double getTotalPrice(String userId);
}
