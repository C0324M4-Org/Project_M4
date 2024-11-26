package com.itachialy.moji_store.config;

import com.itachialy.moji_store.common.EncryptPasswordUtils;
import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.model.Role;
import com.itachialy.moji_store.repository.IAccountRepository;
import com.itachialy.moji_store.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
    private final IAccountRepository iAccountRepository;
    private final IRoleRepository iRoleRepository;

    @Autowired
    public DataSeedingListener(IAccountRepository iAccountRepository, IRoleRepository iRoleRepository) {
        this.iAccountRepository = iAccountRepository;
        this.iRoleRepository = iRoleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (iRoleRepository.findByName("ROLE_ADMIN") == null) {
            iRoleRepository.save(new Role("ROLE_ADMIN"));
        }

        if (iRoleRepository.findByName("ROLE_USER") == null) {
            iRoleRepository.save(new Role("ROLE_USER"));
        }

        //them admin
        if (iAccountRepository.findByEmail("admin@gmail.com") == null) {
            Account admin = new Account();
            admin.setUsername("admin");
            admin.setEmail("admin@gmail.com");
            // mã hóa mật khẩu
            admin.setPassword(EncryptPasswordUtils.EncryptPasswordUtils("123"));
            List<Role> roles = new ArrayList<>();
            roles.add(iRoleRepository.findByName("ROLE_ADMIN"));
            roles.add(iRoleRepository.findByName("ROLE_USER"));
            admin.setRoles(roles);
            iAccountRepository.save(admin);
        }

        //Them user
        if (iAccountRepository.findByEmail("user@gmail.com") == null) {
            Account user = new Account();
            user.setUsername("user");
            user.setEmail("user@gmail.com");
            // mã hóa mật khẩu
            user.setPassword(EncryptPasswordUtils.EncryptPasswordUtils("123"));
            List<Role> roles = new ArrayList<>();
            roles.add(iRoleRepository.findByName("ROLE_USER"));
            user.setRoles(roles);
            iAccountRepository.save(user);
        }
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
