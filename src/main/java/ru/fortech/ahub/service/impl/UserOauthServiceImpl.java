package ru.fortech.ahub.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.fortech.ahub.service.mapper.MapJsonOauth2UserToUserRegistrationDto;
import ru.fortech.ahub.entity.UserEntity;
import ru.fortech.ahub.service.UserOauthService;
import ru.fortech.ahub.service.UserService;
import ru.fortech.ahub.util.JwtTokenUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserOauthServiceImpl implements UserOauthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;


    @Override
    public String createAuthTokenByEmail(String email){
        UserDetails userDetails = userService.loadUserByEmail(email);
        return jwtTokenUtils.generateToken(userDetails);
    }


    @Override
    public UserEntity createNewUser(JSONObject jsonObject){
        MapJsonOauth2UserToUserRegistrationDto mapJsonOauth2UserToUserRegistrationDto = new MapJsonOauth2UserToUserRegistrationDto();
        return userService.createNewUser(mapJsonOauth2UserToUserRegistrationDto.mapToUserRegistrationDto(jsonObject));

    }
}
