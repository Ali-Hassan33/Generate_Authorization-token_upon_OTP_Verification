package org.spring_security.services;

import org.spring_security.entities.Otp;
import org.spring_security.entities.User;
import org.spring_security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findUserOptionalByName(String username) {
        return userRepository.findByName(username);
    }

    public void save(String username, String rawPassword, String email) {
        User user = new User(username, passwordEncoder.encode(rawPassword));
        user.setEmail(email);
        user.setEnabled(true);
        Otp userOtp = new Otp(String.valueOf(new Random().nextInt(1000, 10000)));
        user.setUserOtp(userOtp);
        userRepository.save(user);
    }

    @Override
    public User findUserByName(String username) {
        return findUserOptionalByName(username).orElseThrow();
    }

    @Override
    public String findOtp(String username) {
        Optional<User> optionalUser = this.findUserOptionalByName(username);
        User user = optionalUser.orElseThrow();
        return user.getUserOtp().getOtp();
    }

    @Override
    public String findEmailByName(String name) {
        return userRepository
                .findByName(name)
                .orElseThrow()
                .getEmail();
    }
}
