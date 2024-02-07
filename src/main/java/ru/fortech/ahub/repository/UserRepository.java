package ru.fortech.ahub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fortech.ahub.repository.model.User;

import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
   // Optional<User> findByUserName(String name);
    Optional<User> findByEmail(String email);

}
