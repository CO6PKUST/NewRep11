package ru.fortech.ahub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fortech.ahub.entity.RefreshToken;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID>{
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
