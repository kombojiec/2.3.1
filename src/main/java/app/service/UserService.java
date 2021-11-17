package app.service;

import app.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(int id);
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
}
