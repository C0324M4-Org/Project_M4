package com.itachialy.moji_store.service.impl;

import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.model.Cart;
import com.itachialy.moji_store.model.CartItem;
import com.itachialy.moji_store.model.Product;
import com.itachialy.moji_store.repository.ICartItemRepository;
import com.itachialy.moji_store.repository.ICartRepository;
import com.itachialy.moji_store.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    ICartRepository cartRepository;
    @Autowired
    ICartItemRepository cartItemRepository;

    @Override
    public List<CartItem> getCart(Cart cart) {
        return cartItemRepository.getCartItemsByCartOrderByUpdated_atDesc(cart);
    }

    @Override
    public Cart findCartByAccount(Account account) {
        return cartRepository.findByAccount(account);
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem findCartItemById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    @Override
    public CartItem findProductInListItems(Cart cart, Product product) {
        List<CartItem> cartItems = cartItemRepository.getCartItemsByCart(cart);
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct() == product) {
                return cartItem;
            }
        }
        return null;
    }

    @Override
    public int countCartItem(Cart cart) {
        List<CartItem> cartItems = cartItemRepository.getCartItemsByCart(cart);
        return cartItems.size();
    }

    @Override
    public void addToCart(Cart cart, CartItem cartItem) {
        cart.getCartItems().add(cartItem);
        cartRepository.save(cart);
        cartItemRepository.save(cartItem);
    }

    @Override
    public void removeFromCart(Cart cart, CartItem cartItem) {
        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public int totalPriceCart(Cart cart) {
        List<CartItem> cartItems = cartItemRepository.getCartItemsByCart(cart);
        if(cartItems.isEmpty()) return 0;
        int totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return totalPrice;
    }

    @Override
    public void clearCart(Cart cart){
        cartItemRepository.deleteAllByCart(cart);
    }
}
