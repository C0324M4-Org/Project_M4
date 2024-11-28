package com.itachialy.moji_store.exception;

import org.springframework.security.core.AuthenticationException;

public class AccountBlockedException extends AuthenticationException {
    public AccountBlockedException(String msg) {
        super(msg);
    }
}