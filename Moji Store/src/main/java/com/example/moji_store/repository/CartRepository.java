package com.example.moji_store.repository;

import com.example.moji_store.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(String userId); // Tìm giỏ hàng theo người dùng
}
