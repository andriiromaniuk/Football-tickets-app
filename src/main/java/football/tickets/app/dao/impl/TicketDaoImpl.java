package football.tickets.app.dao.impl;

import football.tickets.app.dao.AbstractDao;
import football.tickets.app.dao.TicketDao;
import football.tickets.app.model.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl extends AbstractDao<Ticket> implements TicketDao {
    public TicketDaoImpl(SessionFactory factory) {
        super(factory, Ticket.class);
    }
}
