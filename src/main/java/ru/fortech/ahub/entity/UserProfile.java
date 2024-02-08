package ru.fortech.ahub.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserProfile {

    private UUID userId;
    private boolean gender;
    private String aboutMe;
    private LocalDateTime registrationDateTime = LocalDateTime.now();
    private UUID userPic;
    private UUID userCover;
    private String numberBankCard;
    private LocalDate birthday;
    private LocalDateTime lastOnline = LocalDateTime.now();
    //private User user;
}
