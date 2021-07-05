package football.tickets.app.dao;

import java.util.List;
import football.tickets.app.model.Order;
import football.tickets.app.model.User;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrdersHistory(User user);
}
