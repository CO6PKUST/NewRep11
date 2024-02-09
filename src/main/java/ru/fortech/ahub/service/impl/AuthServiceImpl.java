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
import ru.fortech.ahub.entity.RefreshToken;
import ru.fortech.ahub.entity.User;
import ru.fortech.ahub.repository.RefreshTokenRepository;
import ru.fortech.ahub.service.RefreshTokenService;
import ru.fortech.ahub.service.UserRepositoryService;
import ru.fortech.ahub.service.dto.*;
import ru.fortech.ahub.exception.AppError;
import ru.fortech.ahub.service.AuthService;
import ru.fortech.ahub.service.UserService;
import ru.fortech.ahub.util.JwtTokenUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepositoryService userRepositoryService;


    private String createAuthTokenByLogin(String login) {
        UserDetails userDetails = userService.loadUserByUsername(login);
        return jwtTokenUtils.generateToken(userDetails);
    }

    @Override
    public ResponseEntity<?> getAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect login or password"), HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(createJwtResponse(authRequest.getLogin()));
    }

    @Override
    public ResponseEntity<?> createNewUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        if (userRegistrationDto.getLogin().matches("^\\+7\\d{10}$") ||
                userRegistrationDto.getLogin().matches("^8\\d{10}$") ||
                userRegistrationDto.getLogin().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){

            if (userRepositoryService.findByLogin(userRegistrationDto.getLogin()).isPresent()) {
                return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "User is already registered"), HttpStatus.BAD_REQUEST);
            }
            userService.createNewUser(userRegistrationDto);

            return ResponseEntity.ok(createJwtResponse(userRegistrationDto.getLogin()));


        }
        else
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "login validation error"), HttpStatus.BAD_REQUEST);

    }
    private JwtResponseTwoToken createJwtResponse(String login) {
        log.info("call method createJwtResponse from AuthServiceImpl");
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(login);
        return JwtResponseTwoToken.builder()
                .accessToken(createAuthTokenByLogin(login))
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }
    @Override
    public ResponseEntity<?> refreshToken(RefreshRequest refreshRequest){
        log.info("call method refreshToken from AuthServiceImpl");
        return ResponseEntity.ok(refreshTokenRepository.findByRefreshToken(refreshRequest.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    JwtResponse jwtResponse = new JwtResponse();
                    if(user.getEmail().isEmpty()){
                        jwtResponse.setAccessToken(createAuthTokenByLogin(user.getPhone()));
                    }else {
                        jwtResponse.setAccessToken(createAuthTokenByLogin(user.getEmail()));
                    }
                    return jwtResponse;
                }).orElseThrow());

    }


}
