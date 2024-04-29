package org.spring_security.services;

import org.spring_security.entities.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserOptionalByName(String username);

    User findUserByName(String username);

    void save(String name, String password, String email);

    String findOtp(String username);

    String findEmailByName(String name);
}
