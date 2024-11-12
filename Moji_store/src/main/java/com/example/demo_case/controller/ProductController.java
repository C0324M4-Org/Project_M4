package com.example.demo_case.controller;


import com.example.demo_case.model.Product;
import com.example.demo_case.service.ICategoryService;
import com.example.demo_case.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("")
    public String showList(Model model){
        model.addAttribute("list", iProductService.findALl());
        return "product/home";
    }

    @GetMapping("show-create")
    public String showCreate(Model model){
        model.addAttribute("productC", new Product());
        model.addAttribute("listC", iCategoryService.findAll());
        return "product/create";
    }
}
