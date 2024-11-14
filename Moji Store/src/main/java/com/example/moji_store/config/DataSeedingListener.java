package com.example.moji_store.config;

import com.example.moji_store.common.EncryptPasswordUtils;
import com.example.moji_store.model.Account;
import com.example.moji_store.model.Role;
import com.example.moji_store.repository.IAccountRepository;
import com.example.moji_store.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private IAccountRepository IAccountRepository;

    @Autowired
    private IRoleRepository IRoleRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (IRoleRepository.findByName("ROLE_ADMIN") == null) {
            IRoleRepository.save(new Role("ROLE_ADMIN"));
        }

        if (IRoleRepository.findByName("ROLE_USER") == null) {
            IRoleRepository.save(new Role("ROLE_USER"));
        }

        //them admin
        if (IAccountRepository.findByEmail("admin@gmail.com") == null) {
            Account admin = new Account();
            admin.setUsername("admin");
            admin.setEmail("admin@gmail.com");
            // mã hóa mật khẩu
            admin.setPassword(EncryptPasswordUtils.EncryptPasswordUtils("123"));
            List<Role> roles = new ArrayList<>();
            roles.add(IRoleRepository.findByName("ROLE_ADMIN"));
            roles.add(IRoleRepository.findByName("ROLE_USER"));
            admin.setRoles(roles);
            IAccountRepository.save(admin);
        }

        //Them user
        if (IAccountRepository.findByEmail("user@gmail.com") == null) {
            Account user = new Account();
            user.setUsername("user");
            user.setEmail("user@gmail.com");
            // mã hóa mật khẩu
            user.setPassword(EncryptPasswordUtils.EncryptPasswordUtils("123"));
            List<Role> roles = new ArrayList<>();
            roles.add(IRoleRepository.findByName("ROLE_USER"));
            user.setRoles(roles);
            IAccountRepository.save(user);
        }
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
