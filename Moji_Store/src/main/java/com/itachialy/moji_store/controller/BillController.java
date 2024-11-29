package com.itachialy.moji_store.controller;


import com.itachialy.moji_store.dto.BillFilterDTO;
import com.itachialy.moji_store.model.Bill;
import com.itachialy.moji_store.model.BillItem;
import com.itachialy.moji_store.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

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
}
