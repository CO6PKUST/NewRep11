package ru.mylearning.myspringprojecttest1.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mylearning.myspringprojecttest1.Entity.PersonRole;
import ru.mylearning.myspringprojecttest1.Repository.PersonRoleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonRoleService {
    private final PersonRoleRepository personRoleRepository;

    public Optional<PersonRole> findByRoleName(String roleName){
        return personRoleRepository.findByRoleName(roleName);
    }
}
