package com.revature.airbnb.Models;

import java.util.Random;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String token;

    private String password;
    private String email;

    // TODO remove for new login strategy
    private final String tokenChars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void generateToken() {
        String toRet = "";
        Random random = new Random();
        for(int i = 0; i < 10; i++) {
            toRet += tokenChars.charAt(random.nextInt(tokenChars.length()));
        }
        this.token = toRet;
    }
}
