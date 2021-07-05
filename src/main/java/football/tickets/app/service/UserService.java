package football.tickets.app.service;

import football.tickets.app.model.User;

public interface UserService {
    User add(User user);

    User get(Long id);

    User findByEmail(String email);
}
