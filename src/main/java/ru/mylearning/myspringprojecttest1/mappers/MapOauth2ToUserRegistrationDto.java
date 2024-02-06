package ru.mylearning.myspringprojecttest1.mappers;

import org.json.JSONObject;
import ru.mylearning.myspringprojecttest1.Dtos.UserRegistrationDto;

import java.util.UUID;

public class MapOauth2ToUserRegistrationDto {



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
