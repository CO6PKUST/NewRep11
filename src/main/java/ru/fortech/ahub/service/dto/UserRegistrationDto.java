package ru.fortech.ahub.service.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
}
