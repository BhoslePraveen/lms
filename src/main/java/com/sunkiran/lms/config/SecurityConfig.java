package com.sunkiran.lms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain customSecurityConfig(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.disable());
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/register", "/instructor/register").permitAll()
            .anyRequest().authenticated()
        );
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }
}