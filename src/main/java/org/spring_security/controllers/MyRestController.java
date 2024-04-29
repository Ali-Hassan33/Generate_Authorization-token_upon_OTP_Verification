package org.spring_security.controllers;

import org.spring_security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    private final UserService userService;

    @Autowired
    public MyRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/greet")
    public String greet() {
        return "Hello!";
    }

    @PostMapping("/signUp")
    public String signUp(@RequestHeader String username, @RequestHeader String password, @RequestHeader String email) {
        userService.save(username, password, email);
        return "user being saved!";
    }

    @PostMapping(path = "/signIn")
    public String signIn(@RequestHeader String username) {
        return "Welcome, " + username;
    }
}
