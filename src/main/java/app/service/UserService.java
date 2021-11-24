package app.service;

import app.entity.Role;
import app.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    List<User> getUsers();
    User getUserById(int id);
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
    User getUserByName(String name);
}
