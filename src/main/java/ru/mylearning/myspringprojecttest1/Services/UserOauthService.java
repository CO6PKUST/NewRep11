package ru.mylearning.myspringprojecttest1.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import ru.mylearning.myspringprojecttest1.Entity.User;
import ru.mylearning.myspringprojecttest1.mappers.MapOauth2ToUserRegistrationDto;
import ru.mylearning.myspringprojecttest1.utils.JwtTokenUtils;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserOauthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;



    public String createAuthTokenByEmail(String email){
        UserDetails userDetails = userService.loadUserByEmail(email);
        return jwtTokenUtils.generateToken(userDetails);
    }



    public User createNewUser(JSONObject jsonObject){
        MapOauth2ToUserRegistrationDto mapOauth2ToUserRegistrationDto = new MapOauth2ToUserRegistrationDto();
        return userService.createNewUser(mapOauth2ToUserRegistrationDto.mapToUserRegistrationDto(jsonObject));

    }
}
