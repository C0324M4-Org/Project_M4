package com.example.moji_store.service.impl;

import com.example.moji_store.model.Account;
import com.example.moji_store.model.Role;
import com.example.moji_store.repository.IAccountRepository;
import com.example.moji_store.repository.IRoleRepository;
import com.example.moji_store.service.security.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IRoleRepository iRoleRepository;
    @Autowired
    private IAccountRepository iAccountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;  // Được inject tự động từ SecurityConfig

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

        // Lấy các role từ cơ sở dữ liệu và gán cho account
        List<Role> roles = account.getRoles().stream()
                .map(role -> iRoleRepository.findByName(role.getName()))
                .collect(Collectors.toList());

        // Gán roles cho account
        account.setRoles(roles);

        iAccountRepository.save(account);
    }
}

