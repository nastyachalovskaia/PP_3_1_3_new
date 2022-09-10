package web.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Initialization {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public Initialization(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        Role admin = new Role(1L, "ROLE_ADMIN");
        Role user = new Role(2L, "ROLE_USER");
        roleService.saveRole(admin);
        roleService.saveRole(user);

        Set<Role> allAccess = new HashSet<>();
        Set<Role> userAccess = new HashSet<>();

        allAccess.add(admin);
        allAccess.add(user);
        userAccess.add(user);

        User user1 = new User("Admin", "Adminov", (byte) 23, "admin", "admin", allAccess, "admin@admin.com");
        User user2 = new User("User", "Userov", (byte) 11, "user2", "user2", userAccess, "user@user.com");

        userService.saveUser(user1);
        userService.saveUser(user2);
    }

}
