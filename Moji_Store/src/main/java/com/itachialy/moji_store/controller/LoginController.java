package com.itachialy.moji_store.controller;

import com.itachialy.moji_store.dto.RegisterDTO;
import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.service.impl.AccountServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class LoginController {
    private final AccountServiceImpl accountService;

    @Autowired
    public LoginController(AccountServiceImpl accountService ){
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public String loginForm(Principal principal, Model model,
                            @RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            HttpSession session) {
        if (principal != null) {
            return "redirect:/";
        }
        if (error != null) {
            String errorMessage = (String) session.getAttribute("errorMessage");
            model.addAttribute("errorMessage", errorMessage);
            session.removeAttribute("errorMessage");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "Bạn đã đăng xuất tài khoản thành công.");
        }
        return "security/login";
    }

    @GetMapping("register-form")
    public String registerForm(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "security/register";
    }

    @PostMapping("register")
    public String addNewAccount(@Valid @ModelAttribute("registerDTO") RegisterDTO registerDTO,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registerDTO", registerDTO);
            return "security/register";
        }
        if (!registerDTO.isPasswordMatch()) {
            model.addAttribute("passwordError", "Mật khẩu và xác nhận mật khẩu không khớp");
            return "security/register";
        }
        if (accountService.existsByEmail(registerDTO.getEmail())) {
            model.addAttribute("emailError", "Email đã tồn tại");
            return "security/register";
        }
        if (accountService.existsByUsername(registerDTO.getUsername())) {
            model.addAttribute("usernameError", "Tên người dùng đã tồn tại");
            return "security/register";
        }

        Account account = new Account();
        BeanUtils.copyProperties(registerDTO, account);
        accountService.save(account);
        return "redirect:/login";
    }

    @GetMapping("/error404")
    public String handleError() {
        return "error404";  // quá quyền sẽ qua đây
    }
}

