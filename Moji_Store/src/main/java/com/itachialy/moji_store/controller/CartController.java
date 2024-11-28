package com.itachialy.moji_store.controller;


import com.itachialy.moji_store.model.*;
import com.itachialy.moji_store.repository.ICartItemRepository;
import com.itachialy.moji_store.service.IBillService;
import com.itachialy.moji_store.service.ICartService;
import com.itachialy.moji_store.service.IProductService;
import com.itachialy.moji_store.service.security.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {
    private ICartService cartService;
    private IProductService productService;
    private IAccountService accountService;
    private IBillService billService;

    @Autowired
    public CartController(ICartService cartService, IProductService productService, IAccountService accountService, IBillService billService) {
        this.cartService = cartService;
        this.productService = productService;
        this.accountService = accountService;
        this.billService = billService;
    }

    private Account getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            return accountService.findByUsername(username);
        }
        return null;
    }

    @GetMapping
    public String index(Model model){
        Cart cart = cartService.findCartByAccount(getCurrentUser());
        model.addAttribute("cart", cartService.findCartByAccount(this.getCurrentUser()));
        model.addAttribute("cartList", cartService.getCart(cart));
        model.addAttribute("user", this.getCurrentUser());
        model.addAttribute("totalPrice", cartService.totalPriceCart(cart));
        model.addAttribute("count", cartService.countCartItem(cart));
        return "cart/index";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, Model model) {
        Account currentUser = this.getCurrentUser();

        if(currentUser != null) {
            Optional<Product> optionalProduct = productService.findById(productId);
            Product product = optionalProduct.get();

            Cart cart = cartService.findCartByAccount(currentUser);

            if(cart == null){
                cart = new Cart();
                cart.setAccount(currentUser);
                cartService.save(cart);
//                return "redirect:/";
            }
            CartItem cartItem = cartService.findProductInListItems(cart, product);

            if(cartService.findProductInListItems(cart, product) != null) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
            }else{
                cartItem = new CartItem(cart, product, 1);
            }
            cartService.addToCart(cart, cartItem);
            return "redirect:/cart";
        }
        return "redirect:/";
    }
    @PostMapping("/increase")
    public String increaseQuantity(@RequestParam Long cartItemId) {
        CartItem cartItem = cartService.findCartItemById(cartItemId);
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartService.save(cartItem);
        return "redirect:/cart";
    }
    @PostMapping("/decrease")
    public String decreaseQuantity(@RequestParam Long cartItemId) {
        CartItem cartItem = cartService.findCartItemById(cartItemId);
        cartItem.setQuantity(cartItem.getQuantity() - 1);

        if(cartItem.getQuantity() >= 1){
            cartService.save(cartItem);
        }else{
            cartService.removeFromCart(cartItem.getCart(), cartItem);
        }
        return "redirect:/cart";
    }

    @PostMapping("/payment")
    public String payment(@RequestParam String note, Model model) {
        List<CartItem> cartItems = cartService.getCart(cartService.findCartByAccount(this.getCurrentUser()));
        Bill newBill = new Bill();
        newBill.setAccount(this.getCurrentUser());
        newBill.setNote(note);
        List<BillItem> list = new ArrayList<>();
        billService.saveBill(newBill);
        for (CartItem cartItem : cartItems) {
//            Bill item moi da den
            BillItem newBillItem = new BillItem();

            newBillItem.setBill(newBill);
            newBillItem.setQuantity(cartItem.getQuantity());
            newBillItem.setProduct(cartItem.getProduct());

            billService.saveBillItem(newBillItem);
            list.add(newBillItem);
        }
        newBill.setBillItems(list);
        billService.saveBill(newBill);
        cartService.clearCart(cartItems.get(0).getCart());

        return "redirect:/";
    }
}
