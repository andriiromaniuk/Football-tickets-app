package football.tickets.app.dao.impl;

import football.tickets.app.config.DataInitializer;
import football.tickets.app.dao.AbstractDao;
import football.tickets.app.dao.ShoppingCartDao;
import football.tickets.app.exception.DataProcessingException;
import football.tickets.app.model.ShoppingCart;
import football.tickets.app.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl extends AbstractDao<ShoppingCart> implements ShoppingCartDao {
    Logger logger = LogManager.getLogger(DataInitializer.class);

    public ShoppingCartDaoImpl(SessionFactory factory) {
        super(factory, ShoppingCart.class);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        logger.info("getByUser method was called");
        try (Session session = factory.openSession()) {
            Query<ShoppingCart> getByUser = session.createQuery(
                    "SELECT DISTINCT sc FROM ShoppingCart sc "
                            + "left join fetch sc.tickets t "
                            + "left join fetch t.gameSession ms "
                            + "left join fetch ms.stadium "
                            + "left join fetch ms.game "
                            + "WHERE sc.user = :user", ShoppingCart.class);
            getByUser.setParameter("user", user);
            logger.info("getByUser method was executed");
            return getByUser.getSingleResult();
        } catch (Exception e) {
            logger.error("Not found shopping cart for user " + user, e);
            throw new DataProcessingException("Not found shopping cart for user " + user, e);
        }
    }
}
