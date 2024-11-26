package com.itachialy.moji_store.repository;

import com.itachialy.moji_store.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // You can define custom query methods here if needed
}