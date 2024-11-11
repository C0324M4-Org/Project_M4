package com.example.moji_store.controller.security;

import com.example.moji_store.model.Account;
import com.example.moji_store.model.Role;
import com.example.moji_store.service.impl.AccountServiceImpl;
import com.example.moji_store.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private AccountServiceImpl accountService;
    @Autowired
    private RoleServiceImpl roleService;
    @GetMapping("login")
    public String loginForm() {
        return "security/login";
    }
    @GetMapping("register")
    public String registerForm(Model model){
        List<Role> roleList = roleService.findAll();

        model.addAttribute("roles", roleList);
        model.addAttribute("accounts", new Account());

        return "security/register";
    }

    @PostMapping("add-account")
    public String addNewAccount(@ModelAttribute("accounts") Account account){
        accountService.save(account);
        return "redirect:/login";
    }
}
