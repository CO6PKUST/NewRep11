package ru.fortech.ahub.service.mapper;

import org.mapstruct.Mapper;
import ru.fortech.ahub.repository.model.UserRole;
import ru.fortech.ahub.entity.UserRoleEntity;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    UserRoleEntity toUserRoleEntity(UserRole userRole);
    UserRole toUserRole(UserRoleEntity userRoleEntity);
}
