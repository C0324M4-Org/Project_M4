package com.itachialy.moji_store.repository;

import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {
    Cart findByAccount(Account account);
}
