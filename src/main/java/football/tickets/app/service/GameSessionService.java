package football.tickets.app.service;

import java.time.LocalDate;
import java.util.List;

import football.tickets.app.model.GameSession;

public interface GameSessionService {
    List<GameSession> findAvailableSessions(Long movieId, LocalDate date);

    GameSession add(GameSession session);

    GameSession get(Long id);

    GameSession update(GameSession gameSession);

    void delete(Long id);
}
