package com.itachialy.moji_store.service;

import com.itachialy.moji_store.model.Product;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAll();
    Page<Product> findAll(Pageable pageable);
    Optional<Product> findById(Long id);
    void save(Product product);
    void deleteById(Long id);

    void addProduct(@Valid Product product);
}
