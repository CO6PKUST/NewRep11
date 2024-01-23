package ru.mylearning.myspringprojecttest1.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.mylearning.myspringprojecttest1.Dtos.*;
import ru.mylearning.myspringprojecttest1.Entity.User;
import ru.mylearning.myspringprojecttest1.Exceptions.AppError;
import ru.mylearning.myspringprojecttest1.Mappers.MapOauth2ToUserRegistrationDto;
import ru.mylearning.myspringprojecttest1.utils.JwtTokenUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;




    private JwtResponse createAuthTokenByUsername(String userName){
        UserDetails userDetails = userService.loadUserByUsername(userName);
        String token = jwtTokenUtils.generateToken(userDetails);
        return new JwtResponse(token);
    }
    private String createAuthTokenByEmail(String email){
        UserDetails userDetails = userService.loadUserByEmail(email);
        return jwtTokenUtils.generateToken(userDetails);
    }


    public ResponseEntity<?> getAuthToken(@RequestBody JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName()
                    , authRequest.getPassword()));
            log.info("проверка пройдена, логин/пароль совпадают");
        } catch (BadCredentialsException e){
            log.info("проверка не пройдена, логин/пароль совпадают");
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(createAuthTokenByUsername(authRequest.getUserName()));

    }

    public ResponseEntity<?> getAuthTokenWithOauth(@AuthenticationPrincipal OAuth2User oAuth2User){
        //OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        log.info("вызов метода getAuthTokenWithOauth2");
        try {
            if(oAuth2User==null){
                log.info("oAuth2User is null");
            }
            if(userService.findByEmail(oAuth2User.getAttribute("email")).isPresent()){
                return ResponseEntity.ok(createAuthTokenByEmail(oAuth2User.getAttribute("email")));
            }
            MapOauth2ToUserRegistrationDto mapOauth2ToUserRegistrationDto = new MapOauth2ToUserRegistrationDto();
            User user = userService.createNewUser(mapOauth2ToUserRegistrationDto.mapToUserRegistrationDto(oAuth2User));
            return ResponseEntity.ok(createAuthTokenByEmail(user.getEmail()));
        }catch (Exception e){
            return null;
        }

    }




    public ResponseEntity<?> createNewUser(@RequestBody UserRegistrationDto userRegistrationDto){
        if(!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if(userService.findByEmail(userRegistrationDto.getEmail()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "пользователь уже зарегистрирован"), HttpStatus.BAD_REQUEST);
        }

        User user = userService.createNewUser(userRegistrationDto);
        return ResponseEntity.ok(new UserDto(user.getUserId(), user.getEmail(), user.getUserName()));
    }





}
