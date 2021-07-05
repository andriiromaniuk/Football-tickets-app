package football.tickets.app.config;

import java.util.Set;
import javax.annotation.PostConstruct;
import football.tickets.app.model.Role;
import football.tickets.app.model.User;
import football.tickets.app.service.RoleService;
import football.tickets.app.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName(Role.RoleName.USER);
        roleService.add(userRole);
        User userAdmin = new User();
        userAdmin.setEmail("admin@i.ua");
        userAdmin.setPassword("admin123");
        userAdmin.setRoles(Set.of(adminRole));
        userService.add(userAdmin);
        User userUser = new User();
        userUser.setEmail("user@i.ua");
        userUser.setPassword("user123");
        userUser.setRoles(Set.of(userRole));
        userService.add(userUser);
    }
}
