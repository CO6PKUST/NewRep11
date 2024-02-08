package ru.fortech.ahub.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fortech.ahub.repository.model.UserDatabaseModel;
import ru.fortech.ahub.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "userRoles", target = "userRoleDatabaseModels")
    UserDatabaseModel toUserDatabaseModel(User user);

    @Mapping(source = "userRoleDatabaseModels", target = "userRoles")
    User toUserEntity(UserDatabaseModel userDatabaseModel);
}
