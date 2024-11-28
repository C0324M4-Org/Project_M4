package com.itachialy.moji_store.service.security;

import com.itachialy.moji_store.exception.AccountBlockedException;
import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final IAccountRepository iAccountRepository;
    @Autowired
    public CustomUserDetailsService(IAccountRepository iAccountRepository) {
        this.iAccountRepository = iAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Account account ;
        // check email hoac user
        if (usernameOrEmail.contains("@")) {
            account = iAccountRepository.findByEmail(usernameOrEmail);
        } else {
            account = iAccountRepository.findByUsername(usernameOrEmail);
        }
        if (account == null) {
            throw new UsernameNotFoundException("Invalid email or username");
        }
        if (account.isDeleted()) {
            throw new AccountBlockedException("Tài khoản của bạn đã bị khóa. Vui lòng liên hệ quản trị viên.");
        }

        List<SimpleGrantedAuthority> authorities = account.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

        return new User(account.getUsername(), account.getPassword(), authorities);
    }

}
