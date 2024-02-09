package ru.fortech.ahub.service;

import ru.fortech.ahub.entity.User;

import java.util.Optional;

public interface UserRepositoryService{
    Optional<User> findByLogin(String login);

}
