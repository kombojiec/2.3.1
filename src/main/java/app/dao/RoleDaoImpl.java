package app.dao;

import app.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> getRoles() {
        return em.createQuery("select role from Role role", Role.class).getResultList();
    }
}
