package football.tickets.app.dao.impl;

import java.util.Optional;

import football.tickets.app.config.DataInitializer;
import football.tickets.app.dao.RoleDao;
import football.tickets.app.dao.AbstractDao;
import football.tickets.app.exception.DataProcessingException;
import football.tickets.app.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    Logger logger = LogManager.getLogger(DataInitializer.class);

    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> getRoleByName(String roleName) {
        logger.info("getRoleByName method was called");
        try (Session session = factory.openSession()) {
            Query<Role> findByRoleName = session.createQuery(
                    "FROM Role WHERE name = :roleName", Role.class);
            findByRoleName.setParameter("roleName", Role.RoleName.valueOf(roleName.toUpperCase()));
            logger.info("getRoleByName method was executed");
            return findByRoleName.uniqueResultOptional();
        } catch (Exception e) {
            logger.info("Role with name " + roleName + " not found", e);
            throw new DataProcessingException("Role with name " + roleName + " not found", e);
        }
    }
}
