package ru.mylearning.myspringprojecttest1.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private int id;
    private String email;
    private String userName;
}
