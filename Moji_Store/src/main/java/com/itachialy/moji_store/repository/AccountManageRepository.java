package com.itachialy.moji_store.repository;


import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class AccountManageRepository implements IAccountManageRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Account> findAll() {
        return em.createQuery("select a from Account a", Account.class).getResultList();
    }

    @Override
    public Account findById(Long id) {
        return em.createQuery("select a from Account a where a.id = :id", Account.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Account> findByRoleName(String roleName) {
        List<Account> accounts = this.findAll();
        List<Account> accountByRole = new ArrayList<>();
        Role role = em.createQuery("select r from Role r where r.name = :roleName", Role.class).setParameter("roleName", roleName).getSingleResult();
        for (Account account : accounts) {
            if (account.getRoles().contains(role)) {
                accountByRole.add(account);
            }
        }
        if (accountByRole.isEmpty()) return null;
        return accountByRole;
    }

    @Override
    public void deactive(Long id) {
        Account account = this.findById(id);
        account.setDeleted(!account.isDeleted());
        em.merge(account);
    }
}
