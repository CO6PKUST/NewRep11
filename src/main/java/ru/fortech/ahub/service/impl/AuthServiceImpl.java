package ru.fortech.ahub.service.impl;

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
import ru.fortech.ahub.repository.UserRepository;
import ru.fortech.ahub.entity.UserEntity;
import ru.fortech.ahub.service.dto.JwtRequest;
import ru.fortech.ahub.service.dto.UserRegistrationDto;
import ru.fortech.ahub.exception.AppError;
import ru.fortech.ahub.service.AuthService;
import ru.fortech.ahub.service.UserService;
import ru.fortech.ahub.util.JwtTokenUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;


    private String createAuthTokenByEmail(String email){
        UserDetails userDetails = userService.loadUserByEmail(email);
        return jwtTokenUtils.generateToken(userDetails);
    }

    @Override
    public ResponseEntity<?> getAuthToken(@RequestBody JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(createAuthTokenByEmail(authRequest.getEmail()));

    }

    @Override
    public ResponseEntity<?> createNewUser(@RequestBody UserRegistrationDto userRegistrationDto){
        if(userRepository.findByEmail(userRegistrationDto.getEmail()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "пользователь уже зарегистрирован"), HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = userService.createNewUser(userRegistrationDto);
        return ResponseEntity.ok(userEntity.getEmail());
    }





}
