package ru.mylearning.myspringprojecttest1.Dtos;

import lombok.Data;
@Data
public class JwtRequest {

    private String email;
    private String password;
}
