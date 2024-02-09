package ru.fortech.ahub.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fortech.ahub.entity.RefreshToken;
import ru.fortech.ahub.entity.User;
import ru.fortech.ahub.repository.RefreshTokenRepository;
import ru.fortech.ahub.service.RefreshTokenService;
import ru.fortech.ahub.service.UserRepositoryService;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepositoryService userRepositoryService;
    @Override
    @Transactional
    public RefreshToken createRefreshToken(String login){
        log.info("call method createRefreshToken from RefreshTokenServiceImpl");

        User user = userRepositoryService.findByLogin(login).orElseThrow();

        Optional<RefreshToken> refreshTokenOpt = refreshTokenRepository.findByUser(user);
        if (refreshTokenOpt.isPresent()){
            log.info("method createRefreshToken from RefreshTokenServiceImpl - delete token ");
            refreshTokenRepository.delete(refreshTokenOpt.orElseThrow());
        }

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .refreshToken(String.valueOf(UUID.randomUUID()))
                .expDate(Instant.now().plusSeconds(6000))
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
            throw new RuntimeException(refreshToken.getRefreshToken() + " was expired");
        }
        return refreshToken;
    }
}
