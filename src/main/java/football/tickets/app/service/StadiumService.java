package football.tickets.app.service;

import java.util.List;

import football.tickets.app.model.Stadium;

public interface StadiumService {
    Stadium add(Stadium stadium);

    Stadium get(Long id);

    List<Stadium> getAll();
}
