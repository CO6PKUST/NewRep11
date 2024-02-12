package ru.fortech.ahub.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegistrationDtoFromOauth implements Serializable {
    private String email;
    private String given_name;
    private String family_name;
}
