package ru.mylearning.myspringprojecttest1.Services;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.mylearning.myspringprojecttest1.Config.PasswordEncoderConfiguration;
import ru.mylearning.myspringprojecttest1.Dtos.RegistrationUserDto;
import ru.mylearning.myspringprojecttest1.Entity.User;
import ru.mylearning.myspringprojecttest1.Entity.UserProfile;
import ru.mylearning.myspringprojecttest1.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final PasswordEncoderConfiguration passwordEncoderConfiguration;



    public Optional<User> findByUserName(String userName){
        log.info("пользователь найден findByUserName");
        return userRepository.findByUserName(userName);
    }
    public Optional<User> findByEmail (String Email){
        log.info("пользователь найден  findByEmail");
        return userRepository.findByEmail(Email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("загрузка пользователя по его имени loadUserByUsername");
        User user = findByUserName(userName).orElseThrow(()-> new UsernameNotFoundException(
                String.format("пользователь '%s' не найден", userName)

        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getHashPassword(),
                user.getUserRoles().stream()
                        .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleName()))
                        .collect(Collectors.toList())
        );
    }

    private String createUserName(){
    return "user" + userRepository.getLastInsertedPersonId();
    }
    public User createNewUser(RegistrationUserDto registrationUserDto){

        User user = new User();
        UserProfile userProfile = new UserProfile();
        Date date = new Date();
        userProfile.setDateRegistration(date);
        userProfile.setLastOnline(date);

        user.setUserName(
                createUserName()
                //registrationUserDto.getEmail().split("@")[0]
                 );
        user.setEmail(registrationUserDto.getEmail());
        user.setHashPassword(passwordEncoderConfiguration.passwordEncoder().encode(registrationUserDto.getPassword()));
        user.setUserRoles(List.of(userRoleService.getUserRole()));
        user.setFirstName(registrationUserDto.getFirstName());
        user.setSecondName(registrationUserDto.getSecondName());
        user.setEnabled(true);
        user.setUserProfile(userProfile);
        userProfile.setUser(user);
        return userRepository.save(user);
    }
}
