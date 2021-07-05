package football.tickets.app.service.impl;

import java.util.List;

import football.tickets.app.dao.StadiumDao;
import football.tickets.app.model.Stadium;
import football.tickets.app.exception.DataProcessingException;
import football.tickets.app.service.StadiumService;
import org.springframework.stereotype.Service;

@Service
public class StadiumServiceImpl implements StadiumService {
    private final StadiumDao stadiumDao;

    public StadiumServiceImpl(StadiumDao stadiumDao) {
        this.stadiumDao = stadiumDao;
    }

    @Override
    public Stadium add(Stadium stadium) {
        return stadiumDao.add(stadium);
    }

    @Override
    public Stadium get(Long id) {
        return stadiumDao.get(id).orElseThrow(
                () -> new DataProcessingException("Can't get cinema hall by id " + id));
    }

    @Override
    public List<Stadium> getAll() {
        return stadiumDao.getAll();
    }
}
