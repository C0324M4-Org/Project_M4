package com.itachialy.moji_store.controller;


import com.itachialy.moji_store.model.Bill;
import com.itachialy.moji_store.model.BillItem;
import com.itachialy.moji_store.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/bill")
public class BillController {
    @Autowired
    private IBillService billService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("bills", billService.findAll());
        return "admin/bill/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        List<BillItem> itemList = billService.getBill(billService.findById(id));
        model.addAttribute("listItems", itemList);
        model.addAttribute("bill", itemList.get(0).getBill());
        return "admin/bill/show";
    }

    @PostMapping("/change-status")
    public String changeStatus(@RequestParam("id") Long id, @RequestParam("status") int status, @RequestParam("url") String url) {
        Bill bill = billService.findById(id);
        bill.setStatus(status);
        billService.saveBill(bill);
        return "redirect:" + url;
    }
}
