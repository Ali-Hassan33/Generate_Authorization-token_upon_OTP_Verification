package org.spring_security.authentication.tokens;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public abstract class CredentialsAuthenticationToken implements Authentication {

    private final String username;

    public boolean isAuthenticated;

    private String password;

    private Object otp;

    public CredentialsAuthenticationToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public CredentialsAuthenticationToken(String username, Object otp) {
        this.username = username;
        this.otp = otp;
    }

    @Override
    public String getName() {
        return this.username;
    }

    @Override
    public final Object getPrincipal() {
        return this.username;
    }

    public String getOtp() {
        return (String) this.otp;
    }

    @Override
    public final Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public final Object getCredentials() {
        return this.password;
    }

    @Override
    public final Object getDetails() {
        return null;
    }

    @Override
    public final boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public final void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }
}
