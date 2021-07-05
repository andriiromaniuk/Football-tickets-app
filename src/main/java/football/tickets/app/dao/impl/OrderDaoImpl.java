package football.tickets.app.dao.impl;

import java.util.List;

import football.tickets.app.config.DataInitializer;
import football.tickets.app.dao.AbstractDao;
import football.tickets.app.dao.OrderDao;
import football.tickets.app.exception.DataProcessingException;
import football.tickets.app.model.Order;
import football.tickets.app.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    Logger logger = LogManager.getLogger(DataInitializer.class);

    public OrderDaoImpl(SessionFactory factory) {
        super(factory, Order.class);
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        logger.info("getOrdersHistory method was called");
        try (Session session = factory.openSession()) {
            Query<Order> getByUser = session.createQuery(
                    "SELECT DISTINCT o FROM orders o "
                            + "join fetch o.tickets t "
                            + "left join fetch t.gameSession ms "
                            + "left join fetch ms.stadium "
                            + "left join fetch ms.game "
                            + "WHERE o.user = :user", Order.class);
            getByUser.setParameter("user", user);
            logger.info("getOrdersHistory method was executed");
            return getByUser.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Not found shopping cart for user " + user, e);
        }
    }
}
