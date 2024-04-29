package org.spring_security.services;

import org.spring_security.entities.User;
import org.spring_security.wrappers.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JPAUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JPAUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userService.findUserOptionalByName(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("username not found!"));
        return new UserDetailsImpl(user);
    }
}
