package app.dao;

import app.entity.Role;
import app.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserDao {
    List<User> getUsers();
    User getUserById(int id);
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
    User getUserByName(String name);
    Collection<Role> getUsersRolesById(int id);
}
