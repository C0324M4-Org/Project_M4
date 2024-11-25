package com.itachialy.moji_store.service.security;

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
        Account user;
        // check email hoac user
        if (usernameOrEmail.contains("@")) {
            user = iAccountRepository.findByEmail(usernameOrEmail);
        } else {
            user = iAccountRepository.findByUsername(usernameOrEmail);
        }
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email or username");
        }
        if(user.isDeleted()){
            throw new UsernameNotFoundException("Your account is blocked");
        }

        List<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

        return new User(user.getUsername(), user.getPassword(), authorities);
    }

}
