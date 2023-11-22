package ru.mylearning.myspringprojecttest1.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mylearning.myspringprojecttest1.Entity.User;
import ru.mylearning.myspringprojecttest1.Entity.UserProfile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileService {

    public UserProfile createUserProfileFromNewUser (User user){
        UserProfile userProfile = new UserProfile();
        userProfile.setRegistrationDateTime(LocalDateTime.now());
        userProfile.setLastOnline(LocalDateTime.now());
        userProfile.setUser(user);
        return userProfile;
    }

}
