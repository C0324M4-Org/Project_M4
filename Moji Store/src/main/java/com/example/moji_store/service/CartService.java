package com.example.moji_store.service;

import com.example.moji_store.model.Cart;
import com.example.moji_store.model.CartItem;
import com.example.moji_store.model.Product;
import com.example.moji_store.repository.CartRepository;
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

        // Kiểm tra nếu sản phẩm đã có trong giỏ hàng
        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProductName().equals(product.getName()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProductName(product.getName());
            newItem.setPrice(product.getPrice());
            newItem.setQuantity(quantity);
            cart.getItems().add(newItem);
        }

        // Cập nhật tổng giá tiền
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

    public Cart updateCartItem(String userId, Long itemId, int quantity) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            CartItem itemToUpdate = cart.getItems().stream()
                    .filter(item -> item.getId().equals(itemId))
                    .findFirst()
                    .orElse(null);
            if (itemToUpdate != null) {
                itemToUpdate.setQuantity(quantity);
                cart.setTotalPrice(cart.getItems().stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum());
                cartRepository.save(cart);
            }
        }
        return cart;
    }

    public void clearCart(String userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            cart.getItems().clear();
            cart.setTotalPrice(0);
            cartRepository.save(cart);
        }
    }
}
