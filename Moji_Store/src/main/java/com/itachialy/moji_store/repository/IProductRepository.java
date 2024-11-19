package com.itachialy.moji_store.repository;

import com.itachialy.moji_store.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCategoryId(Long categoryId);
    Page<Product> findAll(Pageable pageable);

}
