package ru.mylearning.myspringprojecttest1.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table(name = "person")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer userId;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "secondname")
    private String secondName;
    @Column(name = "email")
    private String email;
    @Column(name = "hashpassword")
    private String hashPassword;
    @Column(name = "nickname")
    private String userName;
    @Column(name = "enabled")
    private boolean enabled;
    @ManyToMany
    @JoinTable(
            name = "person_has_role",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<UserRole> userRoles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserProfile userProfile;



}
