package com.itachialy.moji_store.controller;


//import com.itachialy.moji_store.dto.ApiResponse;
import com.itachialy.moji_store.dto.ProductDTO;
import com.itachialy.moji_store.model.Category;
import com.itachialy.moji_store.model.Product;
import com.itachialy.moji_store.service.ICategoryService;
import com.itachialy.moji_store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.Optional;


@Controller
@RequestMapping("/products")
public class ProductController {
    private final IProductService iProductService;
    private final ICategoryService iCategoryService;

    @Autowired
    public ProductController(IProductService iProductService, ICategoryService iCategoryService) {
        this.iProductService = iProductService;
        this.iCategoryService = iCategoryService;
    }


    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = iProductService.searchProducts(keyword);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "/product/home_user";
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        List<Product> products = iProductService.findAll();
        List<Category> categories = iCategoryService.findAllCategories();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "/product/home_user";
    }


    @GetMapping("/details/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        Optional<ProductDTO> product = iProductService.getProductById(id);
        if (product.isEmpty()) {
            model.addAttribute("message", "Product not found with ID: " + id);
        } else {
            model.addAttribute("product", product.get());
        }
        return "/product/product-details";
    }



    @GetMapping
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