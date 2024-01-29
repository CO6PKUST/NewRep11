package ru.mylearning.myspringprojecttest1.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mylearning.myspringprojecttest1.Dtos.OauthCodeDto;
import ru.mylearning.myspringprojecttest1.Dtos.UserRegistrationDto;
import ru.mylearning.myspringprojecttest1.Services.AuthService;
import ru.mylearning.myspringprojecttest1.Dtos.JwtRequest;
import ru.mylearning.myspringprojecttest1.Services.AuthServiceOauth;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final AuthServiceOauth authServiceOauth;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        return authService.getAuthToken(authRequest);
    }
    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody UserRegistrationDto userRegistrationDto){
        return authService.createNewUser(userRegistrationDto);
    }
    @PostMapping("/login/oauth2/code/google")
    public ResponseEntity<?> loginOauth(@RequestBody OauthCodeDto oauthCodeDto){
        log.info("контроллер loginOauth");
        return authServiceOauth.getAuthToken(oauthCodeDto);
    }

}
