package com.itachialy.moji_store.service.security;

import com.itachialy.moji_store.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRoleService {
    Role findByName(String name);
    List<Role> findAll();
}
