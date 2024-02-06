package ru.mylearning.myspringprojecttest1.Services;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.mylearning.myspringprojecttest1.Config.PasswordEncoderConfiguration;
import ru.mylearning.myspringprojecttest1.Dtos.UserRegistrationDto;
import ru.mylearning.myspringprojecttest1.Entity.User;
import ru.mylearning.myspringprojecttest1.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final PasswordEncoderConfiguration passwordEncoderConfiguration;
    private final UserProfileService userProfileService;



//    public Optional<User> findByUserName(String userName){
//        log.info("пользователь найден findByUserName");
//        return userRepository.findByUserName(userName);
//    }
    public Optional<User> findByEmail (String Email){
        log.info("поиск findByEmail");
        return userRepository.findByEmail(Email);
    }
    private org.springframework.security.core.userdetails.User loadUser (User user){
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
        log.info("загрузка пользователя по его почте");
        User user = findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(
                String.format("пользователь '%s' не найден", email)

        ));
        return loadUser(user);
    }
    @Transactional
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException{
        log.info("загрузка пользователя по его имени loadUserByEmail");
        User user = findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(
                String.format("пользователь '%s' не найден", email)

        ));
        return loadUser(user);
    }


    public User createNewUser(UserRegistrationDto userRegistrationDto){
        User user = new User();
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(passwordEncoderConfiguration.passwordEncoder().encode(userRegistrationDto.getPassword()));
        user.setUserRoles(new ArrayList<>(List.of(userRoleService.getUserRole())));
        userProfileService.createUserProfileFromNewUser(user);
        return userRepository.save(user);
    }



}
