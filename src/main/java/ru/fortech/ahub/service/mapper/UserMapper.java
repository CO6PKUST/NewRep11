package ru.fortech.ahub.service.mapper;

import org.mapstruct.Mapper;
import ru.fortech.ahub.repository.model.User;
import ru.fortech.ahub.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserEntity userEntity);
    UserEntity toUserEntity(User user);
}
