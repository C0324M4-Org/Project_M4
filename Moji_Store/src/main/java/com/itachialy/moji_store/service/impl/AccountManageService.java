package com.itachialy.moji_store.service.impl;

import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.repository.IAccountManageRepository;
import com.itachialy.moji_store.service.IAccountManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountManageService implements IAccountManageService {
    IAccountManageRepository accountManageRepository;
    @Autowired
    public AccountManageService(IAccountManageRepository accountManageRepository) {
        this.accountManageRepository = accountManageRepository;
    }
    @Override
    public List<Account> findAll() {
        return accountManageRepository.findAll();
    }
    @Override
    public List<Account> findByRoleName(String roleName) {
        return accountManageRepository.findByRoleName(roleName);
    }
    @Override
    public void deactive(Long id) {
        accountManageRepository.deactive(id);
    }
    @Override
    public Account findById(Long id) {
        return accountManageRepository.findById(id);
    }

    @Override
    public int countAll() {
        return accountManageRepository.countAll();
    }
}
