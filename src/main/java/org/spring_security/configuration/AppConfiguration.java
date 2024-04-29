package org.spring_security.configuration;

import org.spring_security.authentication.filters.TokenAuthenticationFilter;
import org.spring_security.authentication.filters.UsernameOtpAuthenticationFilter;
import org.spring_security.authentication.filters.UsernamePasswordAuthenticationFilter;
import org.spring_security.authentication.providers.TokenProvider;
import org.spring_security.authentication.providers.UsernameOtpAuthenticationProvider;
import org.spring_security.authentication.providers.UsernamePasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppConfiguration {

    private UsernamePasswordAuthenticationProvider usernamePassAuthProvider;

    private UsernameOtpAuthenticationProvider usernameOtpAuthProvider;

    private TokenProvider tokenProvider;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(usernamePassAuthProvider, usernameOtpAuthProvider, tokenProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   UsernamePasswordAuthenticationFilter userPassAuthenticationFilter,
                                                   UsernameOtpAuthenticationFilter usernameOtpAuthenticationFilter,
                                                   TokenAuthenticationFilter tokenAuthenticationFilter,
                                                   AuthenticationManager authenticationManager) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        http.addFilterAt(userPassAuthenticationFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(usernameOtpAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(tokenAuthenticationFilter, UsernameOtpAuthenticationFilter.class);

        http.authenticationManager(authenticationManager);

        http.authenticationProvider(usernamePassAuthProvider)
                .authenticationProvider(usernameOtpAuthProvider)
                .authenticationProvider(tokenProvider);

        return http.build();
    }

    @Autowired
    public void setUsernamePassAuthProvider(UsernamePasswordAuthenticationProvider usernamePassAuthProvider) {
        this.usernamePassAuthProvider = usernamePassAuthProvider;
    }

    @Autowired
    public void setUsernameOtpAuthProvider(UsernameOtpAuthenticationProvider usernameOtpAuthProvider) {
        this.usernameOtpAuthProvider = usernameOtpAuthProvider;
    }

    @Autowired
    public void setTokenProvider(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }
}
