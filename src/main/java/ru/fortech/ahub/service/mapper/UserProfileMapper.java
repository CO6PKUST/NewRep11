package ru.fortech.ahub.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fortech.ahub.repository.model.UserProfileDatabaseModel;
import ru.fortech.ahub.entity.UserProfile;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    @Mapping(source = "user", target = "userDatabaseModel")
    UserProfileDatabaseModel toUserProfileDatabaseModel(UserProfile userProfile);
    @Mapping(source = "userDatabaseModel", target = "user")
    UserProfile toUserProfileEntity(UserProfileDatabaseModel userProfileDatabaseModel);
}
