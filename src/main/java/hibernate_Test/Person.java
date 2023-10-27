package hibernate_Test;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "person_id")
    private int person_id;
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
    @Column(name = "personrole_id")
    private int personRole_id;

    public Person() {
    }

    public Person(String firstName, String secondName, String email,
                  String hashpassword, String nickName, boolean enable, int personrole_id) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.hashPassword = hashpassword;
        this.nickName = nickName;
        this.enabled = enable;
        this.personRole_id = personrole_id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", hashpassword='" + hashPassword + '\'' +
                ", nickName='" + nickName + '\'' +
                ", enable=" + enabled +
                ", personrole_id=" + personRole_id +
                '}';
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHashPassword(String hashpassword) {
        this.hashPassword = hashpassword;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setEnable(boolean enabled) {
        this.enabled = enabled;
    }

    public void setPersonRole_id(int personrole_id) {
        this.personRole_id = personrole_id;
    }
}
