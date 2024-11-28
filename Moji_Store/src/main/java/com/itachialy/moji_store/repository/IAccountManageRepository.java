package com.itachialy.moji_store.repository;

import com.itachialy.moji_store.model.Account;

import java.util.List;

public interface IAccountManageRepository {
    List<Account> findAll();
    Account findById(Long id);
    List<Account> findByRoleName(String roleName);
    void deactive(Long id);
    int countAll();
}
