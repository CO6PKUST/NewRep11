package ru.mylearning.myspringprojecttest1.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/unsecured")
    public String returnUnsecuredData(){
        return "unsecuredData";
    }

    @GetMapping("/secured")
    public String returnSecuredData(){
        return "securedData";
    }

    @GetMapping("/admin")
    public String returnAdminData(){
        return "returnAdminData";
    }

    @GetMapping("/info")
    public String returnInfo(Principal principal){
        return principal.getName();
    }

}
