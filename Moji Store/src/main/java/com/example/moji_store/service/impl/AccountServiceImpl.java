package com.example.moji_store.service.impl;

import com.example.moji_store.service.security.IAccountService;
import com.example.moji_store.model.Account;
import com.example.moji_store.model.Role;
import com.example.moji_store.repository.IAccountRepository;
import com.example.moji_store.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IRoleRepository iRoleRepository;
    @Autowired
    private IAccountRepository iAccountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Account findUserByEmail(String email) {
        return iAccountRepository.findByEmail(email);
    }

    @Override
    public Account findByUsername(String username) {
        return iAccountRepository.findByUsername(username);
    }

    @Override
    public void save(Account account) {

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);

        // Kiểm tra xem ROLE_USER đã có trong cơ sở dữ liệu chưa
        Role userRole = iRoleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            // Nếu chưa có role, tạo role mới và lưu vào cơ sở dữ liệu
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
}

