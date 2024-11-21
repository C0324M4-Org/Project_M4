package com.itachialy.moji_store.service;


import com.itachialy.moji_store.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    void saveCat(Category category);
    void deleteCat(Long id);

}
