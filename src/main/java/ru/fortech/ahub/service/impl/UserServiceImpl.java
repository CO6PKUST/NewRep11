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
import ru.fortech.ahub.service.UserRepositoryService;
import ru.fortech.ahub.service.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;
import ru.fortech.ahub.service.UserProfileService;
import ru.fortech.ahub.service.UserRoleService;
import ru.fortech.ahub.service.UserService;

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
    private final UserRepositoryService userRepositoryService;


    private org.springframework.security.core.userdetails.User loadUser(User user) {
        log.info("call method loadUser from UserServiceImpl");
        Optional<String> email = Optional.ofNullable(user.getEmail());
        if (email.isEmpty()){
            return new org.springframework.security.core.userdetails.User(
                    user.getPhone(),
                    user.getPassword(),
                    user.getUserRoles().stream()
                            .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleName()))
                            .collect(Collectors.toList())
            );
        }else {
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getUserRoles().stream()
                            .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleName()))
                            .collect(Collectors.toList())
            );
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("call method loadUserByUsername from UserServiceImpl");
        User user = userRepositoryService.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("user '%s' is not found", username)));
        return loadUser(user);
    }

//    @Override
//    public User createNewUser(UserRegistrationDto userRegistrationDto) {
//        log.info("call method createNewUser from UserServiceImpl");
//        User user = new User();
//        user.setUserId(UUID.randomUUID());
//        user.setFirstName(userRegistrationDto.getFirstName());
//        user.setLastName(userRegistrationDto.getLastName());
//        user.setPassword(passwordEncoderConfiguration.passwordEncoder().encode(userRegistrationDto.getPassword()));
//        user.setUserRoles(new ArrayList<>(List.of(userRoleService.getUserRole())));
//        user.setEnabled(true);
//        userProfileService.createUserProfileFromNewUser(user);
//        return userRepository.save(checkLoginType(userRegistrationDto, user));
//    }

    @Override
    public User createNewUser(UserRegistrationDto userRegistrationDto) {
        log.info("call method createNewUser from UserServiceImpl");
        User user = User.builder()
                .userId(UUID.randomUUID())
                .firstName(userRegistrationDto.getFirstName())
                .lastName(userRegistrationDto.getLastName())
                .password(passwordEncoderConfiguration.passwordEncoder().encode(userRegistrationDto.getPassword()))
                .userRoles(new ArrayList<>(List.of(userRoleService.getUserRole())))
                .enabled(true)
                .build();
        userProfileService.createUserProfileFromNewUser(user);
        return userRepository.save(checkLoginType(userRegistrationDto, user));
    }
    private User checkLoginType(UserRegistrationDto userRegistrationDto, User user){
        log.info("call method checkLoginType from UserServiceImpl");
        if (userRegistrationDto.getLogin().matches("^\\+7\\d{10}$") || userRegistrationDto.getLogin().matches("^8\\d{10}$")) {
            user.setPhone(userRegistrationDto.getLogin());
            return user;
        }
        user.setEmail(userRegistrationDto.getLogin());
        return user;
    }

}
