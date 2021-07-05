package football.tickets.app.dao.impl;

import java.util.Optional;

import football.tickets.app.config.DataInitializer;
import football.tickets.app.dao.AbstractDao;
import football.tickets.app.dao.UserDao;
import football.tickets.app.exception.DataProcessingException;
import football.tickets.app.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    Logger logger = LogManager.getLogger(DataInitializer.class);

    public UserDaoImpl(SessionFactory factory) {
        super(factory, User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        logger.info("findByEmail method was called");
        try (Session session = factory.openSession()) {
            Query<User> findByEmail = session.createQuery(
                    "FROM User as u "
                            + "left join fetch u.roles WHERE u.email = :email", User.class);
            findByEmail.setParameter("email", email);
            logger.info("find by email method was executed");
            return findByEmail.uniqueResultOptional();
        } catch (Exception e) {
            logger.error("User with email " + email + " not found", e);
            throw new DataProcessingException("User with email " + email + " not found", e);
        }
    }
}
