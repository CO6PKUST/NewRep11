package ru.mylearning.myspringprojecttest1.Services;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.mylearning.myspringprojecttest1.Entity.Person;
import ru.mylearning.myspringprojecttest1.Repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;
    private final PersonRoleService personRoleService;


    public Optional<Person> findByPersonName(String personName){
        return personRepository.findByPersonName(personName);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String personName) throws UsernameNotFoundException {
        Person person = findByPersonName(personName).orElseThrow(()-> new UsernameNotFoundException(
                String.format("пользователь '%s' не найден", personName)
        ));
        return new User(
                person.getNickName(),
                person.getHashPassword(),
                person.getPersonRoles().stream()
                        .map(personRole -> new SimpleGrantedAuthority(personRole.getRoleName()))
                        .collect(Collectors.toList())
        );
    }
    public void createNewPerson(Person person){
        person.setPersonRoles(List.of(personRoleService.findByRoleName("ROLE_USER").get()));
        personRepository.save(person);
    }
}
