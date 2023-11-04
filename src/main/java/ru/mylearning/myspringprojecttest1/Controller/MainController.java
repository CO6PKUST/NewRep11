package ru.mylearning.myspringprojecttest1.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/returnUnsecuredData")
    public String returnUnsecuredData(){
        return "unsecuredData";
    }

    @GetMapping("/returnSecuredData")
    public String returnSecuredData(){
        return "securedData";
    }

    @GetMapping("/returnAdminData")
    public String returnAdminData(){
        return "returnAdminData";
    }

    @GetMapping("/returnInfo")
    public String returnInfo(Principal principal){
        return principal.getName();
    }

}
