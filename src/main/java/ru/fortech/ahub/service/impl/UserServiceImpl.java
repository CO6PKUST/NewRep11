package ru.fortech.ahub.service.impl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.fortech.ahub.config.PasswordEncoderConfiguration;
import ru.fortech.ahub.repository.UserRepository;
import ru.fortech.ahub.entity.UserEntity;
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



    private org.springframework.security.core.userdetails.User loadUser (UserEntity userEntity){
        return new org.springframework.security.core.userdetails.User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getUserRoles().stream()
                        .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleName()))
                        .collect(Collectors.toList())
        );
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("загрузка пользователя по его почте");
        UserEntity userEntity = userMapper.toUserEntity(userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(
                String.format("пользователь '%s' не найден", email))));
        return loadUser(userEntity);
    }
    @Override
    @Transactional
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException{
        log.info("загрузка пользователя по его имени loadUserByEmail");
        UserEntity userEntity = userMapper.toUserEntity(userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(
                String.format("пользователь '%s' не найден", email))));
        return loadUser(userEntity);
    }

    @Override
    public UserEntity createNewUser(UserRegistrationDto userRegistrationDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userRegistrationDto.getFirstName());
        userEntity.setLastName(userRegistrationDto.getLastName());
        userEntity.setEmail(userRegistrationDto.getEmail());
        userEntity.setPassword(passwordEncoderConfiguration.passwordEncoder().encode(userRegistrationDto.getPassword()));
        userEntity.setUserRoles(new ArrayList<>(List.of(userRoleService.getUserRole())));
        userProfileService.createUserProfileFromNewUser(userEntity);
        return userMapper.toUserEntity(userRepository.save(userMapper.toUser(userEntity)));
    }



}
