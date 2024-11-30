package com.itachialy.moji_store.controller;

import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private IAccountService accountService;  // Dịch vụ để lấy thông tin người dùng

    @GetMapping("/profile")
    public String viewProfile(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Account account = accountService.findByUsername(username);

        model.addAttribute("account", account);

        return "user/profile";
    }
}
