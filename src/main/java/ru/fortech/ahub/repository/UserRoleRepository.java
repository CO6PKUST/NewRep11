package ru.fortech.ahub.repository;

import org.springframework.stereotype.Repository;
import ru.fortech.ahub.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
    Optional<UserRole> findByRoleName(String name);
}
