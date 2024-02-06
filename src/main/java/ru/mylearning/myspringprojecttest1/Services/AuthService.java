package ru.mylearning.myspringprojecttest1.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.mylearning.myspringprojecttest1.Dtos.*;
import ru.mylearning.myspringprojecttest1.Entity.User;
import ru.mylearning.myspringprojecttest1.Exceptions.AppError;
import ru.mylearning.myspringprojecttest1.utils.JwtTokenUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;


    private String createAuthTokenByEmail(String email){
        UserDetails userDetails = userService.loadUserByEmail(email);
        return jwtTokenUtils.generateToken(userDetails);
    }


    public ResponseEntity<?> getAuthToken(@RequestBody JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(createAuthTokenByEmail(authRequest.getEmail()));

    }


    public ResponseEntity<?> createNewUser(@RequestBody UserRegistrationDto userRegistrationDto){
        if(userService.findByEmail(userRegistrationDto.getEmail()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "пользователь уже зарегистрирован"), HttpStatus.BAD_REQUEST);
        }

        User user = userService.createNewUser(userRegistrationDto);
        return ResponseEntity.ok(user.getEmail());
    }





}
