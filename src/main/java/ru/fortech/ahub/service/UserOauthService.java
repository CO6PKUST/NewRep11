package ru.fortech.ahub.service;

import org.json.JSONObject;
import ru.fortech.ahub.entity.UserEntity;

public interface UserOauthService {
    String createAuthTokenByEmail(String email);
    UserEntity createNewUser(JSONObject jsonObject);
}
