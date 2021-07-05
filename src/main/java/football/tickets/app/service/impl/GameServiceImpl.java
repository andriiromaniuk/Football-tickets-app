package football.tickets.app.service.impl;

import java.util.List;

import football.tickets.app.dao.GameDao;
import football.tickets.app.model.Game;
import football.tickets.app.exception.DataProcessingException;
import football.tickets.app.service.GameService;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    private final GameDao gameDao;

    public GameServiceImpl(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public Game add(Game game) {
        return gameDao.add(game);
    }

    @Override
    public Game get(Long id) {
        return gameDao.get(id).orElseThrow(
                () -> new DataProcessingException("Can't get movie by id " + id));
    }

    @Override
    public List<Game> getAll() {
        return gameDao.getAll();
    }
}
