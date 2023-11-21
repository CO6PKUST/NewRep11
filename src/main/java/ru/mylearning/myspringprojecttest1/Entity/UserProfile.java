package ru.mylearning.myspringprojecttest1.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "person_profile")
public class UserProfile {
    @Id
    @Column(name = "person_id")
    private Integer userId;
    @Column(name = "sex")
    private boolean sex;
    @Column(name = "aboutme")
    private String aboutMe;
    @Column(name = "dateregistration")
    private Date dateRegistration;
    @Column(name = "personpic")
    private Integer userPic;
    @Column(name = "personcover")
    private Integer userCover;
    @Column(name = "numberbankcard")
    private String numberBankCard;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "lastonline")
    private Date lastOnline;

    @OneToOne
    @MapsId
    @JoinColumn(name = "person_id")
    private User user;

}
