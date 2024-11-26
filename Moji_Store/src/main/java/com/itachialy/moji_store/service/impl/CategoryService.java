package com.itachialy.moji_store.service.impl;

import com.itachialy.moji_store.model.Category;
import com.itachialy.moji_store.repository.ICategoryRepository;
import com.itachialy.moji_store.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void saveCat(Category category) {

    }

    @Override
    public void deleteCat(Long id) {

    }

    public List<Category> getAllCategories() {
return null;
    }
}
