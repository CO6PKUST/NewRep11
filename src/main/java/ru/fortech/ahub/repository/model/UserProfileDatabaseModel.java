package ru.fortech.ahub.repository.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_profile")
public class UserProfileDatabaseModel {
    @Id
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "gender")
    private boolean gender;
    @Column(name = "about_me")
    private String aboutMe;
    @Column(name = "registration_date_time")
    private LocalDateTime registrationDateTime = LocalDateTime.now();
    @Column(name = "user_pic")
    private UUID userPic;
    @Column(name = "user_cover")
    private UUID userCover;
    @Column(name = "number_bank_card")
    private String numberBankCard;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "last_online")
    private LocalDateTime lastOnline = LocalDateTime.now();

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserDatabaseModel userDatabaseModel;
}
