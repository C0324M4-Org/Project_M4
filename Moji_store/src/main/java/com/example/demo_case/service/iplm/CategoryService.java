package com.example.demo_case.service.iplm;

import com.example.demo_case.model.Category;
import com.example.demo_case.repository.ICategoryRepository;
import com.example.demo_case.repository.IProductRepository;
import com.example.demo_case.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository iCategoryRepos;

    @Autowired
    private IProductRepository iProductRepos;

    @Override
    public List<Category> findAll() {
        return iCategoryRepos.findAll();
    }

    @Override
    public Optional<Category> findByid(Long id) {
        return iCategoryRepos.findById(id);
    }

    @Override
    public void save(Category category) {
        iCategoryRepos.save(category);
    }

    @Override
    public void deleteCat(Long id) throws Exception {
//        if(iCategoryRepos.existByCategory(id)){
//            throw new Exception("Không thể xóa mục này");
//        }
        iCategoryRepos.deleteById(id);
    }
}
