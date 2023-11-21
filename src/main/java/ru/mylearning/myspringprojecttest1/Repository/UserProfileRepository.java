package ru.mylearning.myspringprojecttest1.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mylearning.myspringprojecttest1.Entity.UserProfile;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Integer> {
}
