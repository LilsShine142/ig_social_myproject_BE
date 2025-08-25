package com.example.ig_social_myproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${jwt.secret}")
    private String privateKey;

    @Value("${jwt.expiration}")
    private long tokenExpirationMs;

    public String getPrivateKey() {
        return privateKey;
    }
        
    public long getTokenExpirationMs() {
        return tokenExpirationMs;
    }
}