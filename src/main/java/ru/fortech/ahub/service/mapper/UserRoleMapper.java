package ru.fortech.ahub.service.mapper;

import org.mapstruct.Mapper;
import ru.fortech.ahub.entity.UserRole;
import ru.fortech.ahub.repository.model.UserRoleDatabaseModel;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    UserRole toUserRoleEntity(UserRoleDatabaseModel userRoleDatabaseModel);

    UserRoleDatabaseModel toUserRoleDatabaseModel(UserRole userRole);
}
