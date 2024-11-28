package com.itachialy.moji_store.service.impl;

import com.itachialy.moji_store.model.Product;
import com.itachialy.moji_store.repository.IProductRepository;
import com.itachialy.moji_store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
    public Page<Product> findAllByModelType(String modelType, Pageable pageable) {
        return switch (modelType.toLowerCase()) {
            case "gundam" ->
                    iProductRepository.findAllByModelTypeGundam(pageable);
            case "resin" ->
                    iProductRepository.findAllByModelTypeResin(pageable);
            case "pvc" ->
                    iProductRepository.findAllByModelTypePvc(pageable);
            case "nendroi" ->
                    iProductRepository.findAllByModelTypeNendo(pageable);
            default -> Page.empty();
        };
    }

    @Override
    public Page<Product> searchProducts(String query, Pageable pageable) {
        return iProductRepository.findByNameProductContainingOrModelTypeContaining(query, query, pageable);
    }


}
