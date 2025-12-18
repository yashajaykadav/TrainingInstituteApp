package com.nexanova.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                // Removed .anonymous(disable) to allow public access to the endpoints below
                .authorizeHttpRequests(auth -> auth
                        // 1. Allow Login & Register
                        .requestMatchers("/api/auth/**").permitAll()
                        // 2. Allow User List & Bulk Upload (THIS FIXES YOUR ERROR)
                        .requestMatchers("/api/users/**").permitAll()
                        // 3. Block anything else not listed above
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}