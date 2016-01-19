package com.bxtel.security5.auth.exceiption;

public  class TokenIsInValidException extends AuthenticationException {
    public TokenIsInValidException(String msg) {
        super(msg);
    }
    public TokenIsInValidException(String msg, Throwable t) {
        super(msg, t);
    }
}
