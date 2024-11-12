package com.example.demo_case.repository;

import com.example.demo_case.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {
//    boolean existByCategory(Long categoryId);

}
