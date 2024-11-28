package com.itachialy.moji_store.repository;

import com.itachialy.moji_store.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCategoryId(Long categoryId);
    Page<Product> findAll(Pageable pageable);
    @Query("select p from Product p where  p.modelType = 'GUNDAM'")
    Page<Product> findAllByModelTypeGundam(Pageable pageable);
    @Query("select p from Product p where  p.modelType = 'Resin'")
    Page<Product> findAllByModelTypeResin(Pageable pageable);
    @Query("select p from Product p where  p.modelType = 'PVC'")
    Page<Product> findAllByModelTypePvc(Pageable pageable);
    @Query("select p from Product p where  p.modelType = 'NENDROI'")
    Page<Product> findAllByModelTypeNendo(Pageable pageable);
    Page<Product> findByNameProductContainingOrModelTypeContaining(String query, String query1, Pageable pageable);
}





