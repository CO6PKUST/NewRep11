package ru.fortech.ahub.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@RequiredArgsConstructor
public class PasswordEncoderConfiguration {
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
