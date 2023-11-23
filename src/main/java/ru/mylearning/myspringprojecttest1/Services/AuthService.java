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
import ru.mylearning.myspringprojecttest1.Dtos.JwtRequest;
import ru.mylearning.myspringprojecttest1.Dtos.JwtResponse;
import ru.mylearning.myspringprojecttest1.Dtos.UserRegistrationDto;
import ru.mylearning.myspringprojecttest1.Dtos.UserDto;
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
