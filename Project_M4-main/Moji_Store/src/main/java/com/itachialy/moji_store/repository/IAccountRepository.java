package com.itachialy.moji_store.repository;

import com.itachialy.moji_store.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IAccountRepository extends JpaRepository<Account,Long> {
    Account findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Account findByEmail(String email);
}