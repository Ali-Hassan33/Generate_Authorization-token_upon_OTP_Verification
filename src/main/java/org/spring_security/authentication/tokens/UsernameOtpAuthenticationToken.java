package org.spring_security.authentication.tokens;

public class UsernameOtpAuthenticationToken extends CredentialsAuthenticationToken {

    public UsernameOtpAuthenticationToken(String username, Object otp) {
        super(username, otp);
    }

}
