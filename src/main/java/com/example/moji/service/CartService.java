package com.example.moji.service;

import com.example.moji.model.Cart;
import com.example.moji.model.CartItem;
import com.example.moji.model.Product;
import com.example.moji.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart getCartByUser(String userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cartRepository.save(cart);
        }
        return cart;
    }

    public Cart addItemToCart(String userId, Product product, int quantity) {
        Cart cart = getCartByUser(userId);

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProductName(product.getName());
        item.setPrice(product.getPrice());
        item.setQuantity(quantity);

        cart.getItems().add(item);
        cart.setTotalPrice(cart.getItems().stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum());

        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(String userId, Long itemId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            cart.getItems().removeIf(item -> item.getId().equals(itemId));
            cart.setTotalPrice(cart.getItems().stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum());
            cartRepository.save(cart);
        }
        return cart;
    }
}
