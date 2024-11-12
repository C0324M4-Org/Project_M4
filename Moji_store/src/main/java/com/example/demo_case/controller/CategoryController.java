package com.example.demo_case.controller;


import com.example.demo_case.model.Category;
import com.example.demo_case.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
   @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("")
    public String showListCategory(Model model){
        model.addAttribute("list", iCategoryService.findAll() );
        return "/category/list";
    }

    @GetMapping("show-create")
    public String showCreate(Model model){
        model.addAttribute("categories", new Category() );
        return "/category/show_create";
    }



}
