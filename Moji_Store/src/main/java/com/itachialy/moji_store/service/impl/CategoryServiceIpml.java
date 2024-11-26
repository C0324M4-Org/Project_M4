package com.itachialy.moji_store.service.impl;
import com.itachialy.moji_store.model.Category;
import com.itachialy.moji_store.repository.ICategoryRepository;
import com.itachialy.moji_store.repository.IProductRepository;
import com.itachialy.moji_store.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceIpml implements ICategoryService {
    private final ICategoryRepository iCategoryRepository;
    private final IProductRepository iProductRepository;

    @Autowired
    public CategoryServiceIpml(ICategoryRepository iCategoryRepository, IProductRepository iProductRepository) {
        this.iCategoryRepository = iCategoryRepository;
        this.iProductRepository = iProductRepository;
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return iCategoryRepository.findAll(pageable);
    }

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


    @Override
    public void deleteCat(Long id) throws Exception {
        if (iProductRepository.existsByCategoryId(id)) {
            throw new Exception("Không thể xóa mục này vì vẫn còn sản phẩm đang liên kết.");
        }
        iCategoryRepository.deleteById(id);
    }

}
