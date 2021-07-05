package football.tickets.app.dao.impl;

import football.tickets.app.dao.AbstractDao;
import football.tickets.app.dao.StadiumDao;
import football.tickets.app.model.Stadium;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StadiumDaoImpl extends AbstractDao<Stadium> implements StadiumDao {
    public StadiumDaoImpl(SessionFactory factory) {
        super(factory, Stadium.class);
    }
}
