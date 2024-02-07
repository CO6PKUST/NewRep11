package ru.fortech.ahub.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fortech.ahub.repository.UserProfileRepository;
import ru.fortech.ahub.entity.UserEntity;
import ru.fortech.ahub.entity.UserProfileEntity;
import ru.fortech.ahub.service.UserProfileService;
import ru.fortech.ahub.service.mapper.UserProfileMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;
    @Override
    public void createUserProfileFromNewUser (UserEntity userEntity){
        UserProfileEntity userProfileEntity = new UserProfileEntity();
        userProfileEntity.setUserEntity(userEntity);
        userProfileRepository.save(userProfileMapper.toUserProfile(userProfileEntity));
    }

}
