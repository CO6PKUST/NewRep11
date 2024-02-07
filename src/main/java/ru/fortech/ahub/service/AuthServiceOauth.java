package ru.fortech.ahub.service;

import org.springframework.http.ResponseEntity;
import ru.fortech.ahub.service.dto.OauthCodeDto;

public interface AuthServiceOauth {
    ResponseEntity<?> getAuthToken(OauthCodeDto oauthCodeDto);
}
