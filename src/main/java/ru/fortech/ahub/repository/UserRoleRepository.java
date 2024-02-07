package ru.fortech.ahub.repository;
import org.springframework.stereotype.Repository;
import ru.fortech.ahub.repository.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
    Optional<UserRole> findByRoleName(String name);
}
