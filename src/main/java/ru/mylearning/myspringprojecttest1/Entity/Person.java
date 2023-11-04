package ru.mylearning.myspringprojecttest1.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@Data
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer personId;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "secondname")
    private String secondName;
    @Column(name = "email")
    private String email;
    @Column(name = "hashpassword")
    private String hashPassword;
    @Column(name = "nickname")
    private String nickName;
    @Column(name = "enabled")
    private boolean enabled;
    @ManyToMany
    @JoinTable(
            name = "person_has_role",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<PersonRole> personRoles;


}
