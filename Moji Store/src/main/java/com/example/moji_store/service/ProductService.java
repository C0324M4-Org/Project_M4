package com.example.moji_store.service;
import com.example.moji_store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductService iProductService;
    @Override
    public List<Product> findAll() {
        return iProductService.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductService.findById(id);
    }

    @Override
    public void save(Product products) {
        iProductService.save(products);
    }

    @Override
    public void deleteById(Long id) {
        iProductService.deleteById(id);
    }
}
