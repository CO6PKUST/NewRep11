package ru.mylearning.myspringprojecttest1.Dtos;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserRegistrationDto {

    private String email;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String secondName;
}
