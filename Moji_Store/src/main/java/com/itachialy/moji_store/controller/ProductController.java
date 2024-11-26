package com.itachialy.moji_store.controller;


import com.itachialy.moji_store.model.Product;
import com.itachialy.moji_store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class ProductController {
    private final IProductService iProductService;

    @Autowired
    public ProductController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "0") int page, Model model) {
        int sizePage = 8;
        Pageable pageable = PageRequest.of(page, sizePage);
        Page<Product> productPage = iProductService.findAll(pageable);
        model.addAttribute("list", productPage );
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        return "/product/home_user";
    }

}