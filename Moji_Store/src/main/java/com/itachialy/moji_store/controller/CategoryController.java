package com.itachialy.moji_store.controller;


import com.itachialy.moji_store.model.Category;
import com.itachialy.moji_store.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final ICategoryService iCategoryService;

    @Autowired
    public CategoryController(@Qualifier("categoryService") ICategoryService iCategoryService) {
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
