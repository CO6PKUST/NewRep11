package ru.mylearning.myspringprojecttest1.Dtos;
import lombok.Data;

@Data
public class RegistrationUserDto {

    private String email;
    private String password;
    private String confirmPassword;
    private boolean enabled;
    private String firstName;
    private String secondName;


}
