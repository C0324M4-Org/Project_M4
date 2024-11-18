package com.itachialy.moji_store.controller;


import com.itachialy.moji_store.dto.ProductDTO;
import com.itachialy.moji_store.model.Product;
import com.itachialy.moji_store.service.ICategoryService;
import com.itachialy.moji_store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class ProductController {
    private final IProductService iProductService;
    private final ICategoryService iCategoryService;

    @Autowired
    public ProductController(IProductService iProductService, ICategoryService iCategoryService) {
        this.iProductService = iProductService;
        this.iCategoryService = iCategoryService;
    }

    @GetMapping("")
    public String showList(Model model) {
        model.addAttribute("list", iProductService.findAll());
        return "/product/home_user";
    }

    @GetMapping("/show-create")
    public String showCreate(Model model) {
        model.addAttribute("products", new Product());
        model.addAttribute("listC", iCategoryService.findAll());
        return "product/create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("products") Product product, Model model) {

        return "redirect:/";
    }
}