package com.bxtel.security5.auth.exceiption;

public  class AccountIsNotAuthenticatedException extends AuthenticationException {
    public AccountIsNotAuthenticatedException(String msg) {
        super(msg);
    }

    public AccountIsNotAuthenticatedException(String msg, Throwable t) {
        super(msg, t);
    }
}
