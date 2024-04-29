package org.spring_security.authentication.filters;

import jakarta.servlet.http.HttpServletResponse;
import org.spring_security.services.OtpService;
import org.spring_security.wrappers.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

public abstract class CustomAuthenticationFilter extends OncePerRequestFilter {

    protected final AuthenticationManager authenticationManager;

    protected final OtpService otpService;

    protected TokenManager tokenManager;

    @Autowired
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, OtpService otpService, TokenManager tokenManager) {
        this.authenticationManager = authenticationManager;
        this.otpService = otpService;
        this.tokenManager = tokenManager;
    }

    boolean authenticationException(HttpServletResponse response, Authentication authentication) {
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException badCredentialsException) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return true;
        }
        return false;
    }
}
