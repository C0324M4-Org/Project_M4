package com.itachialy.moji_store.service.impl;

import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.repository.IAccountManageRepository;
import com.itachialy.moji_store.service.IAccountManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountManageService implements IAccountManageService {
<<<<<<< HEAD
    @Autowired
    IAccountManageRepository accountManageRepository;

=======
    IAccountManageRepository accountManageRepository;
    @Autowired
    public AccountManageService(IAccountManageRepository accountManageRepository) {
        this.accountManageRepository = accountManageRepository;
    }
>>>>>>> 56bc2d601bfd90cbdaa0c42a3b3bdfe48f02f270
    @Override
    public List<Account> findAll() {
        return accountManageRepository.findAll();
    }
<<<<<<< HEAD

=======
>>>>>>> 56bc2d601bfd90cbdaa0c42a3b3bdfe48f02f270
    @Override
    public List<Account> findByRoleName(String roleName) {
        return accountManageRepository.findByRoleName(roleName);
    }
<<<<<<< HEAD

=======
>>>>>>> 56bc2d601bfd90cbdaa0c42a3b3bdfe48f02f270
    @Override
    public void deactive(Long id) {
        accountManageRepository.deactive(id);
    }
<<<<<<< HEAD

=======
>>>>>>> 56bc2d601bfd90cbdaa0c42a3b3bdfe48f02f270
    @Override
    public Account findById(Long id) {
        return accountManageRepository.findById(id);
    }
}
