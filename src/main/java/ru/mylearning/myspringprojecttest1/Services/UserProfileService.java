package ru.mylearning.myspringprojecttest1.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mylearning.myspringprojecttest1.Repository.UserProfileRepository;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

}
