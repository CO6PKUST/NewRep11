package ru.mylearning.myspringprojecttest1.Dtos;

import jakarta.persistence.*;
import lombok.Data;
import ru.mylearning.myspringprojecttest1.Entity.UserRole;

import java.util.Collection;

@Data
public class RegistrationUserDto {

    private String email;
    private String password;
    private String confirmPassword;
    private String userName;
    private boolean enabled;


}
