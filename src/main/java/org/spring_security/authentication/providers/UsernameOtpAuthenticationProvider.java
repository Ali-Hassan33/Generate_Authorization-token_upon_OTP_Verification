package org.spring_security.authentication.providers;

import org.spring_security.authentication.tokens.CredentialsAuthenticationToken;
import org.spring_security.authentication.tokens.UsernameOtpAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class UsernameOtpAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    @Autowired
    public UsernameOtpAuthenticationProvider(@Qualifier("otpUserDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernameOtpAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CredentialsAuthenticationToken credentialsAuthenticationToken = (CredentialsAuthenticationToken) authentication;
        String name = authentication.getName();
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        String providedOtp = credentialsAuthenticationToken.getOtp();
        String otp = userDetails.getPassword();
        if (providedOtp.equals(otp)) {
            authentication.setAuthenticated(true);
            return authentication;
        }
        throw new BadCredentialsException("bad credentials!");
    }
}
