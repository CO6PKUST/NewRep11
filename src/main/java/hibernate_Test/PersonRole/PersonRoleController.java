package hibernate_Test.PersonRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonRoleController {
    private final PersonRoleService personRoleService;

    @Autowired
    public PersonRoleController(PersonRoleService personRoleService) {
        this.personRoleService = personRoleService;
    }

    @PostMapping("/addPersonRole")
    public void addPersonRole(@RequestBody PersonRole personRole) {
        personRoleService.savePersonRole(personRole);
    }
}
