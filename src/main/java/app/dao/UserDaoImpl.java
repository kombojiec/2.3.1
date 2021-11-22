package app.dao;

import app.entity.Role;
import app.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

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
        em.persist(user);
    }

    @Override
    public void deleteUser(int id) {
        em.remove(em.find(User.class, id));
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> query =  em.createQuery("select u from User u where u.username = :name", User.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public Collection<Role> getUsersRolesById(int id) {
        TypedQuery<List<Role>> query = (TypedQuery<List<Role>>) em.createNativeQuery("SELECT role FROM role WHERE id in (\n" +
                "\tSELECT role_id FROM user_role WHERE user_id = :id\n" +
                ")");
        query.setParameter("id", id);
        return (List)query.getResultList();
    }
}
