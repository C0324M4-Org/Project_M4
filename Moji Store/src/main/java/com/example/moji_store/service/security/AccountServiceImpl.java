package com.example.moji_store.service.security;

import com.example.moji_store.model.Account;
import com.example.moji_store.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findUserByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

}
