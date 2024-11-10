package com.example.moji_store.service.impl;
import com.example.moji_store.model.Product;
import com.example.moji_store.repository.IProductRepository;
import com.example.moji_store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;
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
}
