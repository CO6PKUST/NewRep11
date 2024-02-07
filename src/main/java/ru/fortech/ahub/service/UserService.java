package ru.fortech.ahub.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.fortech.ahub.entity.UserEntity;
import ru.fortech.ahub.service.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;
    UserEntity createNewUser(UserRegistrationDto userRegistrationDto);
}
