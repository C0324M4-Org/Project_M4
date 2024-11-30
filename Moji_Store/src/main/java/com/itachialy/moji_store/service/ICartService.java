package com.itachialy.moji_store.service;

import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.model.Cart;
import com.itachialy.moji_store.model.CartItem;
import com.itachialy.moji_store.model.Product;

import java.util.List;

public interface ICartService {
    List<CartItem> getCart(Cart cart);
    Cart findCartByAccount(Account account);
    void save(Cart cart);
    void save(CartItem cartItem);
    CartItem findCartItemById(Long id);
    CartItem findProductInListItems(Cart cart, Product product);
    int countCartItem(Cart cart);
    void addToCart(Cart cart, CartItem cartItem);
    void removeFromCart(Cart cart, CartItem cartItem);
    int totalPriceCart(Cart cart);
    void clearCart(Cart cart);
}
