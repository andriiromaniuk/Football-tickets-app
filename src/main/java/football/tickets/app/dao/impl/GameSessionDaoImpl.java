package football.tickets.app.dao.impl;

import java.time.LocalDate;
import java.util.List;

import football.tickets.app.config.DataInitializer;
import football.tickets.app.dao.AbstractDao;
import football.tickets.app.dao.GameSessionDao;
import football.tickets.app.exception.DataProcessingException;
import football.tickets.app.model.GameSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GameSessionDaoImpl extends AbstractDao<GameSession> implements GameSessionDao {
    Logger logger = LogManager.getLogger(DataInitializer.class);

    public GameSessionDaoImpl(SessionFactory factory) {
        super(factory, GameSession.class);
    }

    @Override
    public List<GameSession> findAvailableSessions(Long gameId, LocalDate date) {
        logger.info("findAvailableSessions method was called");
        try (Session session = factory.openSession()) {
            Query<GameSession> getAvailableSessions = session.createQuery(
                    "FROM GameSession WHERE id = :id "
                            + "AND DATE_FORMAT(showTime, '%Y-%m-%d') = :date", GameSession.class);
            getAvailableSessions.setParameter("id", gameId);
            getAvailableSessions.setParameter("date", date.toString());
            return getAvailableSessions.getResultList();
        } catch (Exception e) {
            logger.error("Session for game with id "
                    + gameId + " and show date " + date + " not found", e);
            throw new DataProcessingException("Session for game with id "
                    + gameId + " and show date " + date + " not found", e);
        }
    }
}
