package ru.fortech.ahub.service;

import ru.fortech.ahub.entity.User;
import ru.fortech.ahub.service.dto.UserRegistrationDtoFromOauth;

public interface UserOauthService {
    String createAuthTokenByEmail(String email);

    User createNewUser(UserRegistrationDtoFromOauth userRegistrationDtoFromOauth);
}
