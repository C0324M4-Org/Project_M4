package com.example.moji_store.service.security;

import com.example.moji_store.model.Account;
import com.example.moji_store.repository.AccountRepository;
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
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Account user;

        // Kiểm tra nếu nhập vào có @
        if (usernameOrEmail.contains("@")) {
            // Nếu là email, tìm theo email
            user = accountRepository.findByEmail(usernameOrEmail);
        } else {
            // Nếu không phải email, tìm theo username
            user = accountRepository.findByUsername(usernameOrEmail);
        }

        // Nếu không tìm thấy người dùng
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email or username");
        }

        // Lấy quyền của người dùng
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        // Trả về đối tượng User, với email nếu là email, hoặc username nếu là username
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

}
