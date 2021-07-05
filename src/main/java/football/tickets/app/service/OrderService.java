package football.tickets.app.service;

import java.util.List;

import football.tickets.app.model.Order;
import football.tickets.app.model.ShoppingCart;
import football.tickets.app.model.User;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
