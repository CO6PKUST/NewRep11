package ru.fortech.ahub.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fortech.ahub.entity.RefreshToken;
import ru.fortech.ahub.repository.RefreshTokenRepository;
import ru.fortech.ahub.repository.UserRepository;
import ru.fortech.ahub.service.RefreshTokenService;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public RefreshToken createRefreshToken(String username){
        log.info("call method createRefreshToken from RefreshTokenServiceImpl");
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userRepository.findByEmail(username).orElseThrow())
                .refreshToken(String.valueOf(UUID.randomUUID()))
                .expDate(Instant.now().plusSeconds(600))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    @Transactional
    public RefreshToken verifyExpiration(RefreshToken refreshToken){
        log.info("call method VerifyExpiration from RefreshTokenServiceImpl");
        if (refreshToken.getExpDate().compareTo(Instant.now())<0){
            log.info("refreshToken.getExpDate().compareTo(Instant.now())<0 = true");
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException(String.valueOf(refreshToken.getRefreshToken()) + "was expired");
        }
        return refreshToken;
    }
}
