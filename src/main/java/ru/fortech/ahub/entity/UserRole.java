package ru.fortech.ahub.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRole {
    private UUID roleId;
    private String roleName;
}
