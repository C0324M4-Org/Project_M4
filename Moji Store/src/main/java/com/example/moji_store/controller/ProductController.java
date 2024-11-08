package com.example.moji_store.controller;
import com.example.moji_store.model.Product;
import com.example.moji_store.service.ICategoryService;
import com.example.moji_store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class ProductController {
    @Autowired
    private IProductService IproductService;

    @Autowired
    private ICategoryService IcategoryService;

    @GetMapping("")
    public String showList(Model model){
        model.addAttribute("products",IproductService.findAll());
        return "/Products/home_admin";
    }

    @GetMapping("show-create-form")
    public String showCreateForm(Model model){
        model.addAttribute("productC", new Product());
        model.addAttribute("listC", IcategoryService.findAll());
        return "/Products/create";
    }

    @PostMapping("add")
    public String addProduct(@ModelAttribute("productC") Product product, RedirectAttributes redirectAttributes){
        IproductService.save(product);
        redirectAttributes.addFlashAttribute("add","Thêm mới thành công");
        return "redirect:/products";
    }



}
