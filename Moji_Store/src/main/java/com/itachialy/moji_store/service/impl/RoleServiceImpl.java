package com.itachialy.moji_store.service.impl;

import com.itachialy.moji_store.model.Role;
import com.itachialy.moji_store.repository.IRoleRepository;
import com.itachialy.moji_store.service.security.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    private final IRoleRepository iRoleRepository;

    @Autowired
    public RoleServiceImpl(IRoleRepository iRoleRepository) {
        this.iRoleRepository = iRoleRepository;
    }

    @Override
    public Role findByName(String name) {
        return iRoleRepository.findByName(name);
    }

    @Override
    public List<Role> findAll() {
        return iRoleRepository.findAll();
    }
}
