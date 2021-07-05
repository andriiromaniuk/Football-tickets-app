package football.tickets.app.dao.impl;

import java.time.LocalDate;
import java.util.List;

import football.tickets.app.dao.AbstractDao;
import football.tickets.app.dao.GameSessionDao;
import football.tickets.app.exception.DataProcessingException;
import football.tickets.app.model.GameSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GameSessionDaoImpl extends AbstractDao<GameSession> implements GameSessionDao {
    public GameSessionDaoImpl(SessionFactory factory) {
        super(factory, GameSession.class);
    }

    @Override
    public List<GameSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = factory.openSession()) {
            Query<GameSession> getAvailableSessions = session.createQuery(
                    "FROM MovieSession WHERE id = :id "
                            + "AND DATE_FORMAT(showTime, '%Y-%m-%d') = :date", GameSession.class);
            getAvailableSessions.setParameter("id", movieId);
            getAvailableSessions.setParameter("date", date.toString());
            return getAvailableSessions.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Session for movie with id "
                    + movieId + " and show date " + date + " not found", e);
        }
    }
}
