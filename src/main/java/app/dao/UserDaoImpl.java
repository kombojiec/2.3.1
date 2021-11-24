package app.dao;

import app.entity.Role;
import app.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getUsers() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void addUser(User user) {
//        user.setRoles(em.createQuery("SELECT role FROM Role role where role.role = 'ROLE_USER'").getResultList());
        em.persist(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = em.find(User.class, id);
        user.setRoles(null);
        em.remove(user);
    }

    @Override
    public void updateUser(User user) {
//        Query query = em.createQuery("SELECT u FROM User u where u.id = :id");
//        query.setParameter("id", user.getId());
//        User baseUser = (User)query.getSingleResult();
//        user.setRoles(baseUser.getRoles());
        em.merge(user);
    }

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> query =  em.createQuery("select u from User u where u.username = :name", User.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
