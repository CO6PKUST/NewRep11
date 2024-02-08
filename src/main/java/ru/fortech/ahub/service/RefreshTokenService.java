package ru.fortech.ahub.service;

import ru.fortech.ahub.entity.RefreshToken;

public interface RefreshTokenService {
    public RefreshToken createRefreshToken(String username);
    public RefreshToken verifyExpiration(RefreshToken refreshToken);
}
