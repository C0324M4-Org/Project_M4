package com.itachialy.moji_store.service;

import com.itachialy.moji_store.dto.ProductDTO;
import com.itachialy.moji_store.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    Page<Product> findAll(Pageable pageable);
    List<Product> findAll();
    Optional<Product> findById(Long id);
    void save(Product products);
    void deleteById(Long id);

}
