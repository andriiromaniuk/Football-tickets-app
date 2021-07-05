package football.tickets.app.service;

import football.tickets.app.model.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}
