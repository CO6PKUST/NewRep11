package ru.fortech.ahub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fortech.ahub.repository.model.UserDatabaseModel;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDatabaseModel, Integer> {
    // Optional<User> findByUserName(String name);
    Optional<UserDatabaseModel> findByEmail(String email);

}
