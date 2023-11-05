package ru.mylearning.myspringprojecttest1.Dtos;

import lombok.Data;
@Data
public class JwtRequest {

    private String userName;
    private String password;
}
