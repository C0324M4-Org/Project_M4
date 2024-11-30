package com.itachialy.moji_store.repository;

import com.itachialy.moji_store.model.Bill;
import com.itachialy.moji_store.model.BillItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Transactional
public interface IBillItemRepository extends JpaRepository<BillItem, Long> {
    List<BillItem> getBillItemsByBill(Bill bill);
}
