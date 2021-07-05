package football.tickets.app.dao.impl;

import football.tickets.app.dao.AbstractDao;
import football.tickets.app.dao.GameDao;
import football.tickets.app.model.Game;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class GameDaoImpl extends AbstractDao<Game> implements GameDao {
    public GameDaoImpl(SessionFactory factory) {
        super(factory, Game.class);
    }
}
