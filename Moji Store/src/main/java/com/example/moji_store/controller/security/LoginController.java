package com.example.moji_store.controller.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @GetMapping("login")
    public String loginForm() {
        return "security/login";
    }
    @GetMapping("register")
    public String registerForm(){
        return "security/register";
    }
}
