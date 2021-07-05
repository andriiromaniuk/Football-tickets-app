package football.tickets.app.dao;

import java.util.List;
import java.util.Optional;
import football.tickets.app.model.Stadium;

public interface StadiumDao {
    Stadium add(Stadium stadium);

    Optional<Stadium> get(Long id);

    List<Stadium> getAll();
}
