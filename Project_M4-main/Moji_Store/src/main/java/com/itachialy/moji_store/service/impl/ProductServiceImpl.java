package com.itachialy.moji_store.service.impl;

import com.itachialy.moji_store.dto.ProductDTO;
import com.itachialy.moji_store.model.Product;
import com.itachialy.moji_store.repository.IProductRepository;
import com.itachialy.moji_store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Product> searchProducts(String keyword) {
        return iProductRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public Optional<ProductDTO> getProductById(Long id) {
        // Tìm sản phẩm từ cơ sở dữ liệu và chuyển thành DTO
        return iProductRepository.findById(id)
                .map(product -> new ProductDTO(product)); // Chuyển từ Entity sang DTO
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return List.of();
    }

    @Override
    public List<ProductDTO> searchProductsByName(String name) {
        return List.of();
    }

}
