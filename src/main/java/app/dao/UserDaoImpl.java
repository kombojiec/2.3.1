package app.dao;

import app.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Set<User> getUsers() {
        List<User> list = em.createQuery("select u from User u JOIN FETCH u.roles", User.class).getResultList();
        return list.stream().collect(Collectors.toSet());
    }

    @Override
    public User getUserById(int id) {
        return (User) em.createQuery("SELECT u FROM User u JOIN FETCH u.roles where u.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void deleteUser(int id) {
        User user = em.find(User.class, id);
        user.setRoles(null);
        em.remove(user);
    }

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> query =  em.createQuery("select u from User u JOIN FETCH u.roles where  u.username = :name", User.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public void saveUser(User user) {
        em.merge(user);
    }
}
