package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    User getUserById(Long id);
    void update(Long id, User user);
    void remove(Long id);
    List<User> listUsers();
}
