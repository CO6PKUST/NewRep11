package ru.fortech.ahub.entity;

import lombok.Data;

import java.util.Collection;
import java.util.UUID;
@Data
public class UserEntity {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean enabled;
    private Collection<UserRoleEntity> userRoles;
}
