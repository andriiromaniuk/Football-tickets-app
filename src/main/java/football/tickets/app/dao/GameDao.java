package football.tickets.app.dao;

import java.util.List;
import java.util.Optional;

import football.tickets.app.model.Game;

public interface GameDao {
    Game add(Game game);

    Optional<Game> get(Long id);

    List<Game> getAll();
}
