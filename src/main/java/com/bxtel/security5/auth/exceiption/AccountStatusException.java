package com.bxtel.security5.auth.exceiption;

public abstract class AccountStatusException extends AuthenticationException {
    public AccountStatusException(String msg) {
        super(msg);
    }

    public AccountStatusException(String msg, Throwable t) {
        super(msg, t);
    }
}
