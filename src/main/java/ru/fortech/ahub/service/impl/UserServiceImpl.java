package ru.fortech.ahub.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.fortech.ahub.config.PasswordEncoderConfiguration;
import ru.fortech.ahub.entity.User;
import ru.fortech.ahub.repository.UserRepository;
import ru.fortech.ahub.service.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;
import ru.fortech.ahub.service.UserProfileService;
import ru.fortech.ahub.service.UserRoleService;
import ru.fortech.ahub.service.UserService;
import ru.fortech.ahub.service.mapper.UserMapper;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final PasswordEncoderConfiguration passwordEncoderConfiguration;
    private final UserProfileService userProfileService;
    private final UserMapper userMapper;


    private org.springframework.security.core.userdetails.User loadUser(User user) {
        log.info("call method loadUser from UserServiceImpl");
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getUserRoles().stream()
                        .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleName()))
                        .collect(Collectors.toList())
        );
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("call method loadUserByUsername from UserServiceImpl");
        User user = userMapper.toUserEntity(userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                String.format("user '%s' is not found", email))));
        return loadUser(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        log.info("call method loadUserByEmail from UserServiceImpl");
        User user = userMapper.toUserEntity(userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                String.format("user '%s' is not found", email))));
        return loadUser(user);
    }

    @Override
    public User createNewUser(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(passwordEncoderConfiguration.passwordEncoder().encode(userRegistrationDto.getPassword()));
        user.setUserRoles(new ArrayList<>(List.of(userRoleService.getUserRole())));
        user.setEnabled(true);
        userProfileService.createUserProfileFromNewUser(user);
        return userMapper.toUserEntity(userRepository.save(userMapper.toUserDatabaseModel(user)));
    }

}
