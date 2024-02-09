package ru.fortech.ahub.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.fortech.ahub.entity.User;
import ru.fortech.ahub.service.dto.UserRegistrationDto;
import ru.fortech.ahub.service.UserOauthService;
import ru.fortech.ahub.service.UserService;
import ru.fortech.ahub.util.JwtTokenUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserOauthServiceImpl implements UserOauthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;


    @Override
    public String createAuthTokenByEmail(String email) {
        UserDetails userDetails = userService.loadUserByUsername(email);
        return jwtTokenUtils.generateToken(userDetails);
    }


    @Override
    public User createNewUser(JSONObject jsonObject) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        String password = String.valueOf(UUID.randomUUID());
        userRegistrationDto.setLogin(jsonObject.getString("email"));
        userRegistrationDto.setFirstName(jsonObject.getString("given_name"));
        userRegistrationDto.setLastName(jsonObject.getString("family_name"));
        userRegistrationDto.setPassword(password);
        return userService.createNewUser(userRegistrationDto);
    }
}
