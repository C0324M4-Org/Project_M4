package com.example.moji.repository;

import com.example.moji.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(String userId); // Tìm giỏ hàng theo người dùng
}
