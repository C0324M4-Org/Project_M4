package com.example.moji_store.controller;

import com.example.moji_store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlller {
    @Autowired
    private IProductService iProductService;
    @GetMapping("")
    public String showControlPanel(){
        return "management/list_management";
    }

    @GetMapping("/product_manage")
    public String showMenu(Model model){
        model.addAttribute("products",iProductService.findAll());
        return "product/home_admin";
    }
}
