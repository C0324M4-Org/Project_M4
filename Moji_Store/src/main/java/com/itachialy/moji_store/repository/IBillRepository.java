package com.itachialy.moji_store.repository;

import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.model.Bill;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface IBillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findBillsByAccount(Account account);
    @Query("select b from Bill b order by b.created_at desc")
    List<Bill> findAllOrderByDateDesc();
}
