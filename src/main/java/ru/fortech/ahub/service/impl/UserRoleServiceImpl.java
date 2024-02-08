package ru.fortech.ahub.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fortech.ahub.repository.UserRoleRepository;
import ru.fortech.ahub.entity.UserRole;
import ru.fortech.ahub.service.UserRoleService;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Override
    public UserRole getUserRole() {

        return userRoleRepository.findByRoleName("ROLE_USER").orElseThrow();
    }


}
