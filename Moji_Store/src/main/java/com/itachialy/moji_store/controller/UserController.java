package com.itachialy.moji_store.controller;

import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.model.Product;
import com.itachialy.moji_store.service.IAccountService;
import com.itachialy.moji_store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class UserController {
    private  final IProductService iProductService;
    private final IAccountService accountService;
    @Autowired
    public UserController(IProductService iProductService, IAccountService accountService) {
        this.iProductService = iProductService;
        this.accountService = accountService;
    }

    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "") String show,
                           Model model) {
        int sizePage = 8;
        Pageable pageable;

        if ("priceAsc".equals(show)) {
            pageable = PageRequest.of(page, sizePage, Sort.by("price").ascending());
        } else if ("priceDesc".equals(show)) {
            pageable = PageRequest.of(page, sizePage, Sort.by("price").descending());
        } else if ("discount".equals(show)) {
            pageable = PageRequest.of(page, sizePage, Sort.by("discount").descending());
        } else {
            pageable = PageRequest.of(page, sizePage, Sort.by("idProduct").descending());
        }

        Page<Product> productPage = iProductService.findAll(pageable);
        model.addAttribute("list", productPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("show", show);
        return "/user/home_user";
    }
    @GetMapping("/search/{modelType}")
    public String getProductsByModelType(@RequestParam(defaultValue = "0") int page,
                                         @PathVariable String modelType,
                                         Model model) {
        int sizePage = 8;
        Pageable pageable = PageRequest.of(page, sizePage);

        Page<Product> productPage = iProductService.findAllByModelType(modelType, pageable);
        String message = "Không tìm thấy sản phẩm nào cho loại mô hình này.";

        if (productPage.getContent().isEmpty()) {
            model.addAttribute("message", message);
        }

        model.addAttribute("list", productPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("totalElements", productPage.getTotalElements());
        return "/user/home_user";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam("q") String query,
                                 @RequestParam(defaultValue = "0") int page,
                                 Model model) {
        // Chuyển đổi tham số tìm kiếm thành query cho tìm kiếm sản phẩm
        Pageable pageable = PageRequest.of(page, 10, Sort.by("nameProduct"));

        Page<Product> productPage = iProductService.searchProducts(query, pageable);
        if (productPage.isEmpty()) {
            model.addAttribute("message", "Không tìm thấy nội dung bạn yêu cầu.");
        }
        model.addAttribute("list", productPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("q", query);

        return "/user/home_user";
    }

}
