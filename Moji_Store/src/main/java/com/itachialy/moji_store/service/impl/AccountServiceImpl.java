package com.itachialy.moji_store.service.impl;

import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.model.Role;
import com.itachialy.moji_store.repository.IAccountRepository;
import com.itachialy.moji_store.repository.IRoleRepository;
import com.itachialy.moji_store.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class AccountServiceImpl implements IAccountService {
    private final IRoleRepository iRoleRepository;
    private final IAccountRepository iAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(IRoleRepository iRoleRepository, IAccountRepository iAccountRepository, PasswordEncoder passwordEncoder) {
        this.iRoleRepository = iRoleRepository;
        this.iAccountRepository = iAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(Account account) {
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);
        Role userRole = iRoleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role();
            userRole.setName("ROLE_USER");
            iRoleRepository.save(userRole);
        }
        account.setRoles(Collections.singletonList(userRole));
        iAccountRepository.save(account);
    }
    @Override
    public boolean existsByEmail(String email) {
        return iAccountRepository.existsByEmail(email);
    }
    @Override
    public boolean existsByUsername(String username) {
        return iAccountRepository.existsByUsername(username);
    }
    @Override
    public Account findUserByEmail(String email) {
        return iAccountRepository.findByEmail(email);
    }
    @Override
    public Account findByUsername(String username) {
        return iAccountRepository.findByUsername(username);
    }

}

