package ru.mylearning.myspringprojecttest1.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mylearning.myspringprojecttest1.Services.AuthServiceOauth;

import java.security.Principal;
/*
*
* Это тестовый класс контроллер
*
* */
@RestController
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final AuthServiceOauth authServiceOauth;

    @GetMapping("/secured")
    public String returnSecuredData(){
        return "securedData";
    }

    @GetMapping("/")
    public String returnUnsecuredData(){
        return "unsecuredData";
    }

    @GetMapping("/info")
    public String returnInfo(Principal principal){
        return principal.getName();
    }





}
