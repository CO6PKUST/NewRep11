package ru.fortech.ahub.service;

import org.json.JSONObject;
import ru.fortech.ahub.entity.User;

public interface UserOauthService {
    String createAuthTokenByEmail(String email);

    User createNewUser(JSONObject jsonObject);
}
