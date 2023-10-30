package hibernate_Test.PersonRole;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonRoleService {
    private final PersonRoleRepository personRoleRepository;

    @Autowired
    public PersonRoleService(PersonRoleRepository personRoleRepository) {
        this.personRoleRepository = personRoleRepository;
    }
    @Transactional

    public void savePersonRole(PersonRole personRole) {
        personRoleRepository.saveAndFlush(personRole);
    }
}
