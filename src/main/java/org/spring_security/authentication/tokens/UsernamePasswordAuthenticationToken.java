package org.spring_security.authentication.tokens;

public class UsernamePasswordAuthenticationToken extends CredentialsAuthenticationToken {

    public UsernamePasswordAuthenticationToken(String username, String password) {
        super(username, password);
    }
}
