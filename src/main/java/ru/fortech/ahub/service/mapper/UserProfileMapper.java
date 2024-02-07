package ru.fortech.ahub.service.mapper;

import org.mapstruct.Mapper;
import ru.fortech.ahub.repository.model.UserProfile;
import ru.fortech.ahub.entity.UserProfileEntity;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(UserProfileEntity userProfileEntity);
    UserProfileEntity toUserProfileEntity(UserProfile userProfile);
}
