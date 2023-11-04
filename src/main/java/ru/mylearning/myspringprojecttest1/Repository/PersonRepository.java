package ru.mylearning.myspringprojecttest1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mylearning.myspringprojecttest1.Entity.Person;

import java.util.Optional;
@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    Optional<Person> findByPersonName(String name);
}
