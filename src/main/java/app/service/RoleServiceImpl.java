package app.service;

import app.dao.RoleDao;
import app.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDao roleDao;

    @Override
    public Set<Role> getRoles() {
        return roleDao.getRoles();
    }
}
