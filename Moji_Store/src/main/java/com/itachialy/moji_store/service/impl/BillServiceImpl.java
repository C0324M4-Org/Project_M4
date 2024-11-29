package com.itachialy.moji_store.service.impl;

import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.model.Bill;
import com.itachialy.moji_store.model.BillItem;
import com.itachialy.moji_store.repository.IBillItemRepository;
import com.itachialy.moji_store.repository.IBillRepository;
import com.itachialy.moji_store.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements IBillService {
    @Autowired
    IBillRepository billRepository;
    @Autowired
    IBillItemRepository billItemRepository;

    @Override
    public List<Bill> findAll() {
        return billRepository.findAllOrderByDateDesc();
    }

    @Override
    public Bill findById(Long id) {
        return billRepository.findById(id).get();
    }

    @Override
    public List<BillItem> getBill(Bill bill) {
        return billItemRepository.getBillItemsByBill(bill);
    }

    @Override
    public void saveBill(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public void saveBillItem(BillItem billItem) {
        billItemRepository.save(billItem);
    }

    @Override
    public Long countAllBill(){
        return billRepository.count();
    }

    @Override
    public List<Bill> getBillsByAccount(Account account){
        return billRepository.findBillsByAccount(account);
    }
}
