package com.itachialy.moji_store.controller;

import com.itachialy.moji_store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlller {
    private final IProductService iProductService;

    @Autowired
    public AdminControlller(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @GetMapping("")
    public String showControlPanel(Model model){
        model.addAttribute("list", iProductService.findAll());
        return "/product/home_admin";
    }

//    @GetMapping("/product_manage")
//    public String showMenu(Model model){
//        model.addAttribute("products",iProductService.findAll());
//        return "";
//    }
}
