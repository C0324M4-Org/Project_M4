package com.itachialy.moji_store.repository;

import com.itachialy.moji_store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCategoryId(Long id);
    // Các phương thức tùy chỉnh nếu cần
}

