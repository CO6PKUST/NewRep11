package ru.fortech.ahub.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fortech.ahub.entity.User;
import ru.fortech.ahub.repository.UserProfileRepository;
import ru.fortech.ahub.entity.UserProfile;
import ru.fortech.ahub.service.UserProfileService;
import ru.fortech.ahub.service.mapper.UserProfileMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;

    @Override
    public void createUserProfileFromNewUser(User user) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfileRepository.save(userProfileMapper.toUserProfileDatabaseModel(userProfile));
    }


}
