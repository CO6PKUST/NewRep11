package ru.fortech.ahub.service.dto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserRegistrationDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
