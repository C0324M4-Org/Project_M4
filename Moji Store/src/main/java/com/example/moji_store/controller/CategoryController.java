package com.example.moji_store.controller;


import com.example.moji_store.model.Category;
import com.example.moji_store.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final ICategoryService iCategoryService;

    @Autowired
    public CategoryController(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @GetMapping("")
    public String showListCategory(Model model) {
        model.addAttribute("list", iCategoryService.findAll());
        return "/category/list";
    }

    @GetMapping("show-create")
    public String showCreate(Model model) {
        model.addAttribute("categories", new Category());
        return "/category/show_create";
    }


}
