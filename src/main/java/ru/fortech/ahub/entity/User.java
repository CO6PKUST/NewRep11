package ru.fortech.ahub.entity;

import lombok.Data;
import ru.fortech.ahub.repository.model.UserProfileDatabaseModel;

import java.util.Collection;
import java.util.UUID;

@Data
public class User {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean enabled;
    private Collection<UserRole> userRoles;
}
