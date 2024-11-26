package com.itachialy.moji_store.controller;


import com.itachialy.moji_store.model.Role;
import com.itachialy.moji_store.service.IAccountManageService;
import com.itachialy.moji_store.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/account")
public class AccountController {
    @Autowired
    private IAccountManageService accountService;
    @Autowired
    private IRoleService roleService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        List<Role> roles = roleService.findAll();
        model.addAttribute("roleName", "ALL");
        model.addAttribute("roles", roles);
        return "account/index";
    }

    @GetMapping("/{roleName}")
    public String indexByRole(Model model, @RequestParam String roleName) {
        if(roleName.equals("ALL")) {
            model.addAttribute("accounts", accountService.findAll());
        }
        else{
            model.addAttribute("accounts", accountService.findByRoleName(roleName));
        }
        List<Role> roles = roleService.findAll();
        model.addAttribute("roleName", roleName);
        model.addAttribute("roles", roles);
        return "account/index";
    }

    @GetMapping("/{id}/deactive")
    public String deactive(Long id, Model model) {
        model.addAttribute("account", accountService.findById(id));
        return "account/deactive";
    }

    @PostMapping("/deactive")
    public String deactive(@RequestParam Long id) {
        accountService.deactive(id);
        return "redirect:/admin/account";
    }
}
