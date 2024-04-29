package org.spring_security.wrappers;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TokenManager {

    private final List<String> tokens = new ArrayList<>();

    public void addToken(String token) {
        tokens.add(token);
    }

    public boolean containsToken(String token) {
        return tokens.contains(token);
    }

    public String generateToken() {
        String token = UUID.randomUUID().toString();
        this.addToken(token);
        return token;
    }
}
