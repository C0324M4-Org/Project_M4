package com.example.moji_store.service;
import com.example.moji_store.model.Product;
import java.util.List;
import java.util.Optional;


public interface IProductService {
   List<Product> findAll();
    Optional<Product> findById(Long id);
    void save(Product products);
    void deleteById(Long id);
}
