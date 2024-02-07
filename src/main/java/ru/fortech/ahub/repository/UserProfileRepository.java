package ru.fortech.ahub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fortech.ahub.repository.model.UserProfile;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Integer> {
}
