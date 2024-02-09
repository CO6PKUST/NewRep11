package ru.fortech.ahub.service.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDtoFromOauth {
    private String email;
    private String given_name;
    private String family_name;
}
