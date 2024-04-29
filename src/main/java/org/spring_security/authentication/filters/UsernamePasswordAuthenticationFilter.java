package org.spring_security.authentication.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.spring_security.authentication.tokens.UsernamePasswordAuthenticationToken;
import org.spring_security.services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UsernamePasswordAuthenticationFilter extends CustomAuthenticationFilter {

    @Autowired
    public UsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, OtpService otpService) {
        super(authenticationManager, otpService, null);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {
        String username = request.getHeader("Username");
        String password = request.getHeader("Password");
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

        if (this.authenticationException(response, authentication)) return;
        otpService.sendEmail(username);
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.getWriter().write("An otp has been send to the user's email");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !(request.getHeader("Password") != null && request.getServletPath().equals("/signIn"));
    }
}
