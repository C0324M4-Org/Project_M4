package com.itachialy.moji_store.controller;


import com.itachialy.moji_store.model.Category;
import com.itachialy.moji_store.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    private final ICategoryService iCategoryService;

    @Autowired
    public CategoryController(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @GetMapping("")
    public String showListCategory(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Category> categoryPage = iCategoryService.findAll(pageable);

        // Thêm các thuộc tính vào model
        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("page", Map.of(
                "currentPage", categoryPage.getNumber() + 1,
                "totalPages", categoryPage.getTotalPages()));
        return "category/list";
    }

    @GetMapping("show-create")
    public String showCreate(Model model) {
        model.addAttribute("categories", new Category());
        return "category/create";
    }

    @PostMapping("add-category")
    public String addCategory(@ModelAttribute("categories") Category category, RedirectAttributes redirectAttributes){
        iCategoryService.saveCat(category);
        redirectAttributes.addFlashAttribute("add", "Thêm mới loại sản phẩm thành công.");

        return "redirect:/admin/category";
    }

    @GetMapping("show-edit/{id}")
    public String showEdit(@PathVariable Long id, Model model){
        Optional<Category> existingCategory = iCategoryService.findById(id);
        existingCategory.ifPresent(category -> model.addAttribute("categoryU", category));
        return "category/update";
    }
    @PostMapping("update/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("categoryU") Category category,RedirectAttributes redirectAttributes) {
        Optional<Category> existingProduct = iCategoryService.findById(id);
        if (existingProduct.isPresent()) {
            iCategoryService.saveCat(category);
            redirectAttributes.addFlashAttribute("edit", "Cập nhật loại sản phẩm thành công.");

        }
        return "redirect:/admin/category";
    }
    @PostMapping("delete-cat/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Optional<Category> existCategory = iCategoryService.findById(id);
            if (existCategory.isPresent()) {
                iCategoryService.deleteCat(id);
                redirectAttributes.addFlashAttribute("del", "Xóa loại sản phẩm thành công.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/category";
    }


}
