package com.itachialy.moji_store.controller;

import com.itachialy.moji_store.model.Product;
import com.itachialy.moji_store.service.ICategoryService;
import com.itachialy.moji_store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminControlller {
    private final IProductService iProductService;
    private final ICategoryService iCategoryService;
    @Autowired
    public AdminControlller(IProductService iProductService, ICategoryService iCategoryService) {
        this.iProductService = iProductService;
        this.iCategoryService = iCategoryService;
    }



    @GetMapping("")
    public String showControlPanel(Model model){
        model.addAttribute("list", iProductService.findAll());
        return "/admin/home_admin";
    }

    @GetMapping("/product-list")
    public String listProducts(@RequestParam(defaultValue = "1") int page, Model model) {
        Page<Product> productPage = iProductService.findAll(PageRequest.of(page - 1, 5));
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("page", Map.of(
                "currentPage", productPage.getNumber() + 1,
                "totalPages", productPage.getTotalPages()));
        return "admin/list_product";
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
