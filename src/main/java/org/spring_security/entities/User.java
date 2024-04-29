package org.spring_security.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Integer id;

    @Column(name = "username")
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private boolean enabled;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_otp")
    private Otp userOtp;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public Otp getUserOtp() {
        return userOtp;
    }

    public void setUserOtp(Otp userOtp) {
        this.userOtp = userOtp;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
