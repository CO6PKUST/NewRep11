package ru.mylearning.myspringprojecttest1.Mappers;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.mylearning.myspringprojecttest1.Dtos.UserRegistrationDto;
import ru.mylearning.myspringprojecttest1.Entity.User;

@Mapper
public interface UserMapper{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User mapToUser(UserRegistrationDto userRegistrationDto);


}

