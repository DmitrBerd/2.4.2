package web.dao;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface RoleDao {

    void addRole(Role role);

    void removeRoleByID(long id);

    void editRole(Role role);

    List<Role> getAllRoles();

    Role getRole(String role);
}
