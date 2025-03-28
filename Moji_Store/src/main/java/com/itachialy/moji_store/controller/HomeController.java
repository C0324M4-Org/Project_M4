package com.itachialy.moji_store.controller;


import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.model.Bill;
import com.itachialy.moji_store.service.IAccountManageService;
import com.itachialy.moji_store.service.IAccountService;
import com.itachialy.moji_store.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/status-done")
    public String changeStatus(@RequestParam("id") Long id) {
        Bill bill = billService.findById(id);
        bill.setStatus(4);
        billService.saveBill(bill);
        return "redirect:/profile";
    }
}
