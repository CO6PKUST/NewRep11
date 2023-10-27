package hibernate_Test;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "personrole")
public class PersonRole {
    @Id
    @Column(name = "role_id")
    private int role_id;
    @Column(name = "rolename")
    private String roleName;

    public PersonRole() {
    }

    public PersonRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "PersonRole{" +
                "role_id=" + role_id +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
