package com.itachialy.moji_store.service;


import com.itachialy.moji_store.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Page<Category> findAll(Pageable pageable);
    List<Category> findAll();
    Optional<Category> findById(Long id);
    void saveCat(Category category);
    void deleteCat(Long id) throws Exception;
    List<Category> findAllCategories();
}
