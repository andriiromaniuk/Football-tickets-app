package football.tickets.app.dao;

import java.util.List;
import java.util.Optional;

import football.tickets.app.config.DataInitializer;
import football.tickets.app.exception.DataProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<T> {
    protected final SessionFactory factory;
    private final Class<T> clazz;
    Logger logger = LogManager.getLogger(DataInitializer.class);

    public AbstractDao(SessionFactory factory, Class<T> clazz) {
        this.factory = factory;
        this.clazz = clazz;
    }

    public T add(T t) {
        logger.info(clazz.getSimpleName() + " add method was called");
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            logger.info(clazz.getSimpleName() + "add method was executed");
            return t;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't insert "
                    + clazz.getSimpleName() + " " + t, e);
            throw new DataProcessingException("Can't insert "
                    + clazz.getSimpleName() + " " + t, e);
        }
    }

    public Optional<T> get(Long id) {
        logger.info(clazz.getSimpleName() + " get method was called");
        try (Session session = factory.openSession()) {
            Optional<T> t = Optional.ofNullable(session.get(clazz, id));
            logger.info("get method was executed");
            return t;
        } catch (Exception e) {
            logger.error("Can't get "
                    + clazz.getSimpleName() + ", id: " + id, e);
            throw new DataProcessingException("Can't get "
                + clazz.getSimpleName() + ", id: " + id, e);
        }
    }

    public List<T> getAll() {
        logger.info(clazz.getSimpleName() + " getAll method was called");
        try (Session session = factory.openSession()) {
            List<T> resultList = session.createQuery("from " + clazz.getSimpleName(), clazz).getResultList();
            logger.info("getAll method was executed");
            return resultList;
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all "
                + clazz.getSimpleName() + "s from db", e);
        }
    }

    public T update(T t) {
        logger.info(clazz.getSimpleName() + "update method was called");
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            logger.info(clazz.getSimpleName() + "update method was executed");
            return t;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't update "
                    + clazz.getSimpleName() + " " + t, e);
            throw new DataProcessingException("Can't update "
                    + clazz.getSimpleName() + " " + t, e);
        }
    }

    public void delete(Long id) {
        logger.info(clazz.getSimpleName() + " delete method was called");
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            T movieSession = session.get(clazz, id);
            session.delete(movieSession);
            transaction.commit();
            logger.info(clazz.getSimpleName() + " delete method was executed");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't delete "
                    + clazz.getSimpleName() + " with id: " + id, e);
            throw new DataProcessingException("Can't delete "
                    + clazz.getSimpleName() + " with id: " + id, e);
        }
    }
}
