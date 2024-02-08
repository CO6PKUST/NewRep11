package ru.fortech.ahub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Data
@Entity
@Table(name = "user_role")
@Slf4j
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id")
    private UUID roleId;
    @Column(name = "role_name")
    private String roleName;
}

