package ru.fortech.ahub.entity;
import lombok.Data;

import java.util.UUID;
@Data
public class UserRoleEntity {
    private UUID roleId;
    private String roleName;
}
