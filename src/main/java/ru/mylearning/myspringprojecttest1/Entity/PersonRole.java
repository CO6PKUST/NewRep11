package ru.mylearning.myspringprojecttest1.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Entity
@Table(name = "personrole")
@Slf4j
public class PersonRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "rolename")
    private String roleName;
}
