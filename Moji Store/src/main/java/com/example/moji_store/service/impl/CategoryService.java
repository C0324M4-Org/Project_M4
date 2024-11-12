package com.example.moji_store.service.impl;

import com.example.moji_store.model.Category;
import com.example.moji_store.repository.ICategoryRepository;
import com.example.moji_store.repository.IProductRepository;
import com.example.moji_store.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository iCategoryRepository;
    @Autowired
    private IProductRepository iProductRepository;
    @Override
    public List<Category> findAll() {
        return iCategoryRepository.findAll();
    }
    @Override
    public Optional<Category> findById(Long id) {
        return iCategoryRepository.findById(id);
    }
    @Override
    public void saveCat(Category category) {
        iCategoryRepository.save(category);
    }

    public static class CannotDeleteCategoryException extends RuntimeException {
        public CannotDeleteCategoryException(String message) {
            super(message);
        }
    }

    @Override
    public void deleteCat(Long id) {
        if (iProductRepository.existsByCategoryId(id)) {
            throw new CannotDeleteCategoryException("Không thể xóa mục này");
        }
        iCategoryRepository.deleteById(id);
    }

}
