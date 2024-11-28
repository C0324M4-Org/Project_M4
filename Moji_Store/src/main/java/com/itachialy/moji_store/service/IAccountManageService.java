package com.itachialy.moji_store.service;

import com.itachialy.moji_store.model.Account;

import java.util.List;

public interface IAccountManageService {
    List<Account> findAll();
    List<Account> findByRoleName(String roleName);
    void deactive(Long id);
    Account findById(Long id);
    int countAll();
}
