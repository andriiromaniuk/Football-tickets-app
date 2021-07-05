package football.tickets.app.dao;

import java.util.Optional;
import football.tickets.app.model.Role;

public interface RoleDao {
    Role add(Role role);

    Optional<Role> getRoleByName(String roleName);
}
