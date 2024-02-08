package ru.fortech.ahub.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fortech.ahub.service.dto.OauthCodeDto;
import ru.fortech.ahub.service.dto.UserRegistrationDto;
import ru.fortech.ahub.service.AuthService;
import ru.fortech.ahub.service.dto.JwtRequest;
import ru.fortech.ahub.service.OauthService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final OauthService oauthService;

    @PostMapping("/")
    public ResponseEntity<?> loginUsernamePassword(@RequestBody JwtRequest authRequest) {
        log.info("calling the loginUsernamePassword controller");
        return authService.getAuthToken(authRequest);
    }

    @PostMapping("/reg")
    public ResponseEntity<?> registrationNewUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        log.info("calling the registrationNewUser controller");
        return authService.createNewUser(userRegistrationDto);
    }

    @PostMapping("/oauth2")
    public ResponseEntity<?> loginOauth(@RequestBody OauthCodeDto oauthCodeDto) {
        log.info("calling the loginOauth controller");
        return oauthService.getAuthToken(oauthCodeDto);
    }
}