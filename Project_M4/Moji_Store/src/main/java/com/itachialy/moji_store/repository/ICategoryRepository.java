package com.itachialy.moji_store.repository;

import com.itachialy.moji_store.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
