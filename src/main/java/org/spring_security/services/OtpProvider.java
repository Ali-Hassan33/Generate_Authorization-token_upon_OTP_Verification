package org.spring_security.services;

import org.spring_security.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Component
public class OtpProvider {

    private final UserService userService;

    @Autowired
    public OtpProvider(UserService userService) {
        this.userService = userService;
    }

    public String getOtp(String username) {
        return userService.findOtp(username);
    }

    @Transactional
    public void updateOtp(String username) {
        User user = userService.findUserByName(username);
        String newOtp = String.valueOf(new Random().nextInt(1000, 10000));
        user.getUserOtp().setOtp(newOtp);
    }
}
