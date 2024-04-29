package org.spring_security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

    private final JavaMailSender mailSender;

    private final OtpProvider otpProvider;

    private final UserService userService;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public OtpService(JavaMailSender mailSender, OtpProvider otpProvider, UserService userService) {
        this.mailSender = mailSender;
        this.otpProvider = otpProvider;
        this.userService = userService;
    }

    public void sendEmail(String recipientUsername) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(userService.findEmailByName(recipientUsername));
        mailMessage.setSubject("OTP for Sign-in");
        mailMessage.setText(recipientUsername + ", your OTP is: " + otpProvider.getOtp(recipientUsername));
        mailSender.send(mailMessage);
    }

    public void refreshOtp(String recipientUsername) {
        otpProvider.updateOtp(recipientUsername);
    }
}
