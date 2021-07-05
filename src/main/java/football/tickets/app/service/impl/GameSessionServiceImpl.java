package football.tickets.app.service.impl;

import java.time.LocalDate;
import java.util.List;

import football.tickets.app.dao.GameSessionDao;
import football.tickets.app.model.GameSession;
import football.tickets.app.exception.DataProcessingException;
import football.tickets.app.service.GameSessionService;
import org.springframework.stereotype.Service;

@Service
public class GameSessionServiceImpl implements GameSessionService {
    private final GameSessionDao gameSessionDao;

    public GameSessionServiceImpl(GameSessionDao gameSessionDao) {
        this.gameSessionDao = gameSessionDao;
    }

    @Override
    public List<GameSession> findAvailableSessions(Long movieId, LocalDate date) {
        return gameSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public GameSession add(GameSession session) {
        return gameSessionDao.add(session);
    }

    @Override
    public GameSession get(Long id) {
        return gameSessionDao.get(id).orElseThrow(
                () -> new DataProcessingException("Session with id " + id + " not found"));
    }

    @Override
    public GameSession update(GameSession gameSession) {
        return gameSessionDao.update(gameSession);
    }

    @Override
    public void delete(Long id) {
        gameSessionDao.delete(id);
    }
}
