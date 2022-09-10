package web.service;

import web.model.Role;

import java.util.List;

public interface RoleService {

    public List<Role> getAllRoles();

    public Role getRoleByName(String name);

    public void saveRole(Role role);

}
