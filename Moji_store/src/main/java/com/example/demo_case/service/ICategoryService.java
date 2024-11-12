package com.example.demo_case.service;
import com.example.demo_case.model.Category;
import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();
    Optional<Category> findByid(Long id);
    void save(Category category);
    void deleteCat(Long id) throws Exception;

}
