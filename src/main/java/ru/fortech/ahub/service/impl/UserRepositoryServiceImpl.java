package ru.fortech.ahub.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fortech.ahub.entity.User;
import ru.fortech.ahub.repository.UserRepository;
import ru.fortech.ahub.service.UserRepositoryService;

import java.util.Optional;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryServiceImpl implements UserRepositoryService {
    private final UserRepository userRepository;



    @Override
    public Optional<User> findByLogin(String login) {
        log.info("call method findByLogin from UserRepositoryServiceImpl");

        if (login.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            log.info("call method userRepository.findByEmail(login) from UserRepositoryServiceImpl");
            return userRepository.findByEmail(login);
        }
            log.info("call method userRepository.findByPhone(login) from UserRepositoryServiceImpl");
            return userRepository.findByPhone(login);
    }

}
