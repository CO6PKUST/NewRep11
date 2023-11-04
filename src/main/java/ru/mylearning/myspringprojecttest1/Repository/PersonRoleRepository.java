package ru.mylearning.myspringprojecttest1.Repository;
import ru.mylearning.myspringprojecttest1.Entity.PersonRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRoleRepository extends JpaRepository<PersonRole, Integer>{
    Optional<PersonRole> findByRoleName(String name);
}
