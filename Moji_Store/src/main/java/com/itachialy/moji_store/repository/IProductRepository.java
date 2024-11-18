package com.itachialy.moji_store.repository;

import com.itachialy.moji_store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCategoryId(Long categoryId);

}
