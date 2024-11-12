package com.example.demo_case.service;
import com.example.demo_case.model.Product;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findALl();
    Optional<Product> findById(Long id);
    void save(Product product);
    void delete(Long id);

}
