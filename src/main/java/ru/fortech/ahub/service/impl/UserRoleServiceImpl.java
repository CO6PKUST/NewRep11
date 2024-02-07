package ru.fortech.ahub.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fortech.ahub.repository.UserRoleRepository;
import ru.fortech.ahub.entity.UserRoleEntity;
import ru.fortech.ahub.service.UserRoleService;
import ru.fortech.ahub.service.mapper.UserRoleMapper;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserRoleMapper userRoleMapper;
    @Override
    public UserRoleEntity getUserRole(){

        return userRoleMapper.toUserRoleEntity(userRoleRepository.findByRoleName("ROLE_USER").orElseThrow());
    }


}
