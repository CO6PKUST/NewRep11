package ru.fortech.ahub.service;

import org.springframework.http.ResponseEntity;
import ru.fortech.ahub.service.dto.*;

public interface AuthService {
    ResponseEntity<?> getAuthToken(JwtRequest authRequest);

    ResponseEntity<?> createNewUser(UserRegistrationDto userRegistrationDto);
    ResponseEntity<?> refreshToken(RefreshRequest refreshRequest);
}
