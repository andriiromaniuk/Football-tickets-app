package football.tickets.app.service;

import java.util.List;

import football.tickets.app.model.Game;

public interface GameService {
    Game add(Game game);

    Game get(Long id);

    List<Game> getAll();
}
