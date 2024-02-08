package ru.fortech.ahub.repository;

import org.springframework.stereotype.Repository;
import ru.fortech.ahub.repository.model.UserRoleDatabaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleDatabaseModel, Integer> {
    Optional<UserRoleDatabaseModel> findByRoleName(String name);
}
