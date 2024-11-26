package com.itachialy.moji_store.service.impl;

import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    // Additional methods for managing accounts can be added here
}