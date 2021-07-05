package football.tickets.app.service;

import football.tickets.app.model.GameSession;
import football.tickets.app.model.ShoppingCart;
import football.tickets.app.model.User;

public interface ShoppingCartService {
    void addSession(GameSession gameSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
