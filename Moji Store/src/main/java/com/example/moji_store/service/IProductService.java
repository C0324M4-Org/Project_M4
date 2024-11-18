package com.example.moji_store.service;

import com.example.moji_store.dto.ProductDTO;
import com.example.moji_store.model.Product;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IProductService {
   List<Product> findAll();
    Optional<Product> findById(Long id);
    void save(Product products);
    void deleteById(Long id);
    Product convertToProduct(ProductDTO productDTO) throws IOException;
    String saveImage(MultipartFile image) throws IOException;
}
