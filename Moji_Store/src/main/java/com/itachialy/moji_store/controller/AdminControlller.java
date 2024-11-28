package com.itachialy.moji_store.controller;

import com.itachialy.moji_store.service.IAccountManageService;
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
    private final IAccountManageService iAccountManageService;

    @Autowired
    public AdminControlller(IProductService iProductService, IAccountManageService iAccountManageService) {
        this.iProductService = iProductService;
        this.iAccountManageService = iAccountManageService;
    }

    @GetMapping("")
    public String showControlPanel(Model model){
        model.addAttribute("list", iProductService.findAll());
        model.addAttribute("accountCounter", iAccountManageService.countAll());
        return "/admin/home_admin";
    }

//    @GetMapping("/product_manage")
//    public String showMenu(Model model){
//        model.addAttribute("products",iProductService.findAll());
//        return "";
//    }
}
