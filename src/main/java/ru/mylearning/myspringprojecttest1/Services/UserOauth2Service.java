package ru.mylearning.myspringprojecttest1.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import ru.mylearning.myspringprojecttest1.Entity.User;
import ru.mylearning.myspringprojecttest1.Mappers.MapOauth2ToUserRegistrationDto;
import ru.mylearning.myspringprojecttest1.utils.JwtTokenUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserOauth2Service {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;

    private String createAuthTokenByEmail(String email){
        UserDetails userDetails = userService.loadUserByEmail(email);
        return jwtTokenUtils.generateToken(userDetails);
    }


    public String getAuthToken(OAuth2User oAuth2User){
        log.info("вызов метода getAuthTokenWithOauth");

        try {
            if(oAuth2User==null){
                log.info("oAuth2User is null");
            }
            String email = oAuth2User.getAttribute("email");
            if(userService.findByEmail(email).isPresent()){
                return createAuthTokenByEmail(email);
            }
            return createAuthTokenByEmail(createNewUser(oAuth2User).getEmail());
        }catch (Exception e){
            return null;
        }
    }
    private User createNewUser(OAuth2User oAuth2User){
        MapOauth2ToUserRegistrationDto mapOauth2ToUserRegistrationDto = new MapOauth2ToUserRegistrationDto();
        return userService.createNewUser(mapOauth2ToUserRegistrationDto.mapToUserRegistrationDto(oAuth2User));

    }
}
