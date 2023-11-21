package ru.mylearning.myspringprojecttest1.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mylearning.myspringprojecttest1.Entity.User;

import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUserName(String name);
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT last_value FROM person_person_id_seq", nativeQuery = true)
    Integer getLastInsertedPersonId();
}
