package org.spring_security.authentication.providers;

import org.spring_security.authentication.tokens.Token;
import org.spring_security.wrappers.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider implements AuthenticationProvider {

    private final TokenManager tokenManager;

    @Autowired
    public TokenProvider(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(Token.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Token token = (Token) authentication;
        if (tokenManager.containsToken(token.getToken())) {
            token.setAuthenticated(true);
            return token;
        }
        throw new BadCredentialsException("invalid token!");
    }
}
