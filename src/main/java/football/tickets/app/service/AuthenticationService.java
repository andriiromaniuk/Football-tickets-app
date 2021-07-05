package football.tickets.app.service;

import football.tickets.app.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
