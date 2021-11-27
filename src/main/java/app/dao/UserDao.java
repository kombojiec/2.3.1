package app.dao;

import app.entity.User;

import java.util.Set;

public interface UserDao {
    Set<User> getUsers();
    User getUserById(int id);
//    void addUser(User user);
    void deleteUser(int id);
//    void updateUser(User user);
    User getUserByName(String name);
    void saveUser(User user);
}
