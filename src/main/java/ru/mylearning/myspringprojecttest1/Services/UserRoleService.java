package ru.mylearning.myspringprojecttest1.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mylearning.myspringprojecttest1.Entity.UserRole;
import ru.mylearning.myspringprojecttest1.Repository.UserRoleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRole getUserRole(){
        return userRoleRepository.findByRoleName("ROLE_USER").orElseThrow();
    }


}
