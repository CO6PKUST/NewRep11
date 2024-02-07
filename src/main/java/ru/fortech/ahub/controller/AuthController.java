package ru.fortech.ahub.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fortech.ahub.service.dto.OauthCodeDto;
import ru.fortech.ahub.service.dto.UserRegistrationDto;
import ru.fortech.ahub.service.AuthService;
import ru.fortech.ahub.service.dto.JwtRequest;
import ru.fortech.ahub.service.AuthServiceOauth;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final AuthServiceOauth authServiceOauth;

    @PostMapping("/")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        return authService.getAuthToken(authRequest);
    }
    @PostMapping("/reg")
    public ResponseEntity<?> createNewUser(@RequestBody UserRegistrationDto userRegistrationDto){
        return authService.createNewUser(userRegistrationDto);
    }
    @PostMapping("/oauth2")
    public ResponseEntity<?> loginOauth(@RequestBody OauthCodeDto oauthCodeDto){
        log.info("calling the loginOauth");
        return authServiceOauth.getAuthToken(oauthCodeDto);
    }
}
