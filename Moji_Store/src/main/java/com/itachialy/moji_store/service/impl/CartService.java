package com.itachialy.moji_store.service.impl;

import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.model.Cart;
import com.itachialy.moji_store.model.CartItem;
import com.itachialy.moji_store.model.Product;
import com.itachialy.moji_store.repository.CartItemRepository;
import com.itachialy.moji_store.repository.CartRepository; // Ensure you have a CartRepository for database access
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart getCartByUser(Account user) {
        return cartRepository.findByUser(user).orElse(new Cart(user)); // Ensure you're using Account type here
    }
    public List<CartItem> getCartProducts(Account id){
        Cart cart = cartRepository.findByUser(id)
                          .orElseThrow(() -> new RuntimeException("Cart not found for user with ID: " + id));
return cartItemRepository.findByCart(cart); //
    }
    public int calculateTotalPrice(List<CartItem> cartItems) {
        int totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getQuantity() * cartItem.getProduct().getPrice(); // Tính tổng giá trị của các sản phẩm
        }
        return totalPrice;
    }

    public void addItemToCart(Account user, Product product, int quantity) {
        Cart cart = getCartByUser(user);
        CartItem cartItem = new CartItem(product, quantity);
        cart.addItem(cartItem); // Call addItem method from Cart class
        cartRepository.save(cart); // Save updated cart to repository
    }

    public void updateCartItem(Account user, Long productId, int quantity) {
        Cart cart = getCartByUser(user);
        if (cart != null) {
            CartItem cartItem = cart.getItems().stream()
                    .filter(item -> item.getProduct().getId().equals(productId))
                    .findFirst()
                    .orElse(null);
            if (cartItem != null) {
                cartItem.setQuantity(quantity);
                cartRepository.save(cart); // Save updated cart to repository
            }
        }
    }

    public void removeItemFromCart(Account user, Long productId) {
        Cart cart = getCartByUser(user);
        if (cart != null) {
            cart.removeItem(productId); // Call removeItem from Cart class
            cartRepository.save(cart); // Save updated cart to repository
        }
    }

    public void clearCart(Account user) {
        Cart cart = getCartByUser(user);
        if (cart != null) {
            cart.clearItems(); // Call clearItems from Cart class
            cartRepository.save(cart); // Save updated cart to repository
        }
    }
}