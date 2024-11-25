package com.itachialy.moji_store.repository;

import com.itachialy.moji_store.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account,Long> {
    Account findByEmail(String email);
    Account findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}