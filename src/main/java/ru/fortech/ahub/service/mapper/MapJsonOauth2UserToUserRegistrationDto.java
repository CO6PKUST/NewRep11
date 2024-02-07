package ru.fortech.ahub.service.mapper;

import org.json.JSONObject;
import ru.fortech.ahub.service.dto.UserRegistrationDto;

import java.util.UUID;

public class MapJsonOauth2UserToUserRegistrationDto {



    public UserRegistrationDto mapToUserRegistrationDto(JSONObject jsonObject){
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        String password = String.valueOf(UUID.randomUUID());
        userRegistrationDto.setEmail(jsonObject.getString("email"));
        userRegistrationDto.setFirstName(jsonObject.getString("given_name"));
        userRegistrationDto.setLastName(jsonObject.getString("family_name"));
        userRegistrationDto.setPassword(password);
        return userRegistrationDto;
    }


}
