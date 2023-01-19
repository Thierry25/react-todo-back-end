package com.thierry.marcelin.restfulservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicSecurity {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
        .authorizeHttpRequests(auth -> 
                        auth.anyRequest().authenticated())
        .csrf().disable()
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(
            session -> 
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
    }
}
