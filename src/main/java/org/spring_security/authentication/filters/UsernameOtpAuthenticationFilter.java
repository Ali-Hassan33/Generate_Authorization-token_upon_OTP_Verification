package org.spring_security.authentication.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.spring_security.authentication.tokens.UsernameOtpAuthenticationToken;
import org.spring_security.services.OtpService;
import org.spring_security.wrappers.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UsernameOtpAuthenticationFilter extends CustomAuthenticationFilter {

    @Autowired
    public UsernameOtpAuthenticationFilter(AuthenticationManager authenticationManager, OtpService otpService, TokenManager tokenManager) {
        super(authenticationManager, otpService, tokenManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException {
        String username = request.getHeader("Username");
        String otp = request.getHeader("OTP");
        Authentication authentication = new UsernameOtpAuthenticationToken(username, otp);

        if (this.authenticationException(response, authentication)) return;
        otpService.refreshOtp(username);
        response.addHeader("Authorization", tokenManager.generateToken());
        response.getWriter().write("token generated!");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !(request.getHeader("OTP") != null && request.getServletPath().equals("/signIn"));
    }
}
