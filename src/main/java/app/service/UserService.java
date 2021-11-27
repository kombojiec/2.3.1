package app.service;

import app.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {
    Set<User> getUsers();
    User getUserById(int id);
//    void addUser(User user);
    void deleteUser(int id);
//    void updateUser(User user);
    User getUserByName(String name);
    void saveUser(User user);
}
