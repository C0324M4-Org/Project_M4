package com.itachialy.moji_store.repository;

import com.itachialy.moji_store.model.Cart;
import com.itachialy.moji_store.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByCart(Cart cart);
}
