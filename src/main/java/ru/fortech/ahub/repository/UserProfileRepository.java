package ru.fortech.ahub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fortech.ahub.entity.UserProfile;

import java.util.UUID;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, UUID> {
}
