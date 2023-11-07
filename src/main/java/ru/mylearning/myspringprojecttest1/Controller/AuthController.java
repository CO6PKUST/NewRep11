package ru.mylearning.myspringprojecttest1.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mylearning.myspringprojecttest1.Dtos.JwtResponse;
import ru.mylearning.myspringprojecttest1.Exceptions.AppError;
import ru.mylearning.myspringprojecttest1.Services.UserService;
import ru.mylearning.myspringprojecttest1.Dtos.JwtRequest;
import ru.mylearning.myspringprojecttest1.utils.JwtTokenUtils;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

@PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName()
                    , authRequest.getPassword()));
            log.info("проверка пройдена, логин/пароль совпадают");
        } catch (BadCredentialsException e){
            log.info("проверка не пройдена, логин/пароль совпадают");
           return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUserName());
        String token = jwtTokenUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));


    }
}
