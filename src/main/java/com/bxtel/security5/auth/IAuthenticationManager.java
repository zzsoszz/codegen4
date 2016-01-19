package com.bxtel.security5.auth;

import com.bxtel.security5.auth.exceiption.AuthenticationException;

public interface IAuthenticationManager {
    IAuthenticationResponse authenticate(AbsAuthenticationRequest authentication) throws AuthenticationException;
}