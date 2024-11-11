package com.example.moji_store.repository;

import com.example.moji_store.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account,Long> {
    Account findByEmail(String email);
    Account findByUsername(String username);
}