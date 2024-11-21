package com.itachialy.moji_store.service.impl;

import com.itachialy.moji_store.dto.ProductDTO;
import com.itachialy.moji_store.model.Product;
import com.itachialy.moji_store.repository.IProductRepository;
import com.itachialy.moji_store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {
    private final IProductRepository iProductRepository;

    @Autowired
    public ProductServiceImpl(IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
    }


    @Override
    public Page<Product> findAll(Pageable pageable) {
        return iProductRepository.findAll(pageable);
    }

    @Override
    public List<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepository.findById(id);
    }

    @Override
    public void save(Product products) {
        iProductRepository.save(products);
    }

    @Override
    public void deleteById(Long id) {
        iProductRepository.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) {
        return iProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }



}