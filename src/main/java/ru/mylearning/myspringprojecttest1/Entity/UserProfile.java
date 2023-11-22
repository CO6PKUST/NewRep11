package ru.mylearning.myspringprojecttest1.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "person_profile")
public class UserProfile {
    @Id
    @Column(name = "person_id")
    private Integer userId;
    @Column(name = "sex")
    private boolean sex;
    @Column(name = "about_me")
    private String aboutMe;
    @Column(name = "registration_date_time")
    private LocalDateTime registrationDateTime;
    @Column(name = "person_pic")
    private Integer userPic;
    @Column(name = "person_cover")
    private Integer userCover;
    @Column(name = "number_bank_card")
    private String numberBankCard;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "last_online")
    private LocalDateTime lastOnline;

    @OneToOne
    @MapsId
    @JoinColumn(name = "person_id")
    private User user;

}
