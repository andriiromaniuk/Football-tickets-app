package football.tickets.app.dao.impl;

import java.util.Optional;

import football.tickets.app.dao.RoleDao;
import football.tickets.app.dao.AbstractDao;
import football.tickets.app.exception.DataProcessingException;
import football.tickets.app.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> getRoleByName(String roleName) {
        try (Session session = factory.openSession()) {
            Query<Role> findByRoleName = session.createQuery(
                    "FROM Role WHERE name = :roleName", Role.class);
            findByRoleName.setParameter("roleName", Role.RoleName.valueOf(roleName.toUpperCase()));
            return findByRoleName.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Role with name " + roleName + " not found", e);
        }
    }
}