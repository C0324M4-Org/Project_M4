package com.example.moji_store.service.impl;

import com.example.moji_store.model.Role;
import com.example.moji_store.repository.IRoleRepository;
import com.example.moji_store.service.security.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository iRoleRepository;
    @Override
    public List<Role> findAll() {
        return iRoleRepository.findAll();
    }
}
