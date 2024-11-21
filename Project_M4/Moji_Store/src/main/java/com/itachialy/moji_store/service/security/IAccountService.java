package com.itachialy.moji_store.service.security;

import com.itachialy.moji_store.model.Account;
import org.springframework.stereotype.Service;

@Service
public interface IAccountService {
    Account findUserByEmail(String email);
    Account findByUsername(String username);
    void save(Account account);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}