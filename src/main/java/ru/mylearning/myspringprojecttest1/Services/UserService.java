package ru.mylearning.myspringprojecttest1.Services;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.mylearning.myspringprojecttest1.Entity.User;
import ru.mylearning.myspringprojecttest1.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;


    public Optional<User> findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
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
    public void createNewUser(User user){
        user.setUserRoles(List.of(userRoleService.findByRoleName("ROLE_USER").get()));
        userRepository.save(user);
    }
}
