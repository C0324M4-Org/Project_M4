package com.itachialy.moji_store.service;

import com.itachialy.moji_store.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IAccountService {
    Account findUserByEmail(String email);
    Account findByUsername(String username);
    void save(Account account);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Account findByUsernameOrEmail(String email, String username);
    Account findByUsernameOrEmail(String emailOrUsername);
}