package com.example.moji_store.service.security;

import com.example.moji_store.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IRoleService {
    Role findByName(String name);

    List<Role> findAll();
}
