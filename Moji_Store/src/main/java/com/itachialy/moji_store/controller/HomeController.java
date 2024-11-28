package com.itachialy.moji_store.controller;


import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.repository.IBillRepository;
import com.itachialy.moji_store.service.IAccountManageService;
import com.itachialy.moji_store.service.IBillService;
import com.itachialy.moji_store.service.security.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    IAccountManageService accountManageService;
    @Autowired
    IAccountService accountService;
    @Autowired
    IBillService billService;

    private Account getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            return accountService.findByUsername(username);
        }
        return null;
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("user", getCurrentUser());
        model.addAttribute("bills", billService.getBillsByAccount(getCurrentUser()));
        return "profile/profile";
    }
}
