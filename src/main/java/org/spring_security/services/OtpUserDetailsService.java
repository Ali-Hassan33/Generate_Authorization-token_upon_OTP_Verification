package org.spring_security.services;

import org.spring_security.entities.User;
import org.spring_security.repositories.UserRepository;
import org.spring_security.wrappers.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OtpUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public OtpUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByName(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("user not found!"));
        String otp = user.getUserOtp().getOtp();
        user.setPassword(otp);
        return new UserDetailsImpl(user);
    }
}
