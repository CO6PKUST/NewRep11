package ru.mylearning.myspringprojecttest1.Mappers;

import org.springframework.security.oauth2.core.user.OAuth2User;
import ru.mylearning.myspringprojecttest1.Dtos.UserRegistrationDto;

import java.util.UUID;

public class MapOauth2ToUserRegistrationDto {



    public UserRegistrationDto mapToUserRegistrationDto(OAuth2User oAuth2User){
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        String password = String.valueOf(UUID.randomUUID());
        userRegistrationDto.setEmail(oAuth2User.getAttribute("email"));
        userRegistrationDto.setFirstName(oAuth2User.getAttribute("given_name"));
        userRegistrationDto.setSecondName(oAuth2User.getAttribute("family_name"));
        userRegistrationDto.setPassword(password);
        userRegistrationDto.setConfirmPassword(password);
        return userRegistrationDto;
    }


}
