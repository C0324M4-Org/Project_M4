package com.example.moji_store.service.security;

import com.example.moji_store.model.Account;

public interface AccountService {
    Account findUserByEmail(String email);
    Account findByUsername(String username);
}