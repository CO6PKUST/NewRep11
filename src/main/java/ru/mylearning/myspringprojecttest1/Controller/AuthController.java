package ru.mylearning.myspringprojecttest1.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import ru.mylearning.myspringprojecttest1.Dtos.UserRegistrationDto;
import ru.mylearning.myspringprojecttest1.Services.AuthService;
import ru.mylearning.myspringprojecttest1.Dtos.JwtRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        return authService.getAuthToken(authRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody UserRegistrationDto userRegistrationDto){
        return authService.createNewUser(userRegistrationDto);
    }

    @GetMapping("/authWithOauth")

    public ResponseEntity<?> authWithOauth2(@AuthenticationPrincipal OAuth2User oAuth2User){
        log.info("вызов controller authWithOauth2");
        return authService.getAuthTokenWithOauth(oAuth2User);
    }





}
