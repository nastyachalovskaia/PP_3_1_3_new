package web.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    @Query("select distinct u from User u left join fetch u.roles")
    public List<User> getAllUsers();

    @Query("select u from User u join fetch u.roles where u.id =:id")
    public User getUserById(Long id);

    @Query("select  u from User u join fetch u.roles where u.login = :login")
    public User getUserByLogin(String login);

    public void deleteById(Long id);


}
