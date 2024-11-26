package com.itachialy.moji_store.repository;

import com.itachialy.moji_store.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username); // Custom method to find an account by username
}