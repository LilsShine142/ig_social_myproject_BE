package com.example.ig_social_myproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomSecurityConfigAPI {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Cho phép truy cập API không cần login (tạm thời cho phép tất cả có enpoint
                        // bắt đầu bằng /api/, sau này sẽ sửa lại kỹ hơn)
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable()); // Tắt CSRF cho API

        return http.build();
    }
}
