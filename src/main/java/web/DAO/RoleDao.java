package web.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

    public Role getRoleByName(String name);

}
