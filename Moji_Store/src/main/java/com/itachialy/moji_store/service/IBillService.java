package com.itachialy.moji_store.service;

import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.model.Bill;
import com.itachialy.moji_store.model.BillItem;
import com.itachialy.moji_store.repository.IBillItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IBillService {
    List<Bill> findAll();

    Bill findById(Long id);

    List<BillItem> getBill(Bill bill);
    void saveBill(Bill bill);
    void saveBillItem(BillItem billItem);
    Long countAllBill();
    List<Bill> getBillsByAccount(Account account);
}
