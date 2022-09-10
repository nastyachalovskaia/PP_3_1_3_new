package web.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Set;



@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name should not be empty")
    @Size(min = 3, max = 200, message = "Minimum name length should be 3.")
    private String name;

    @Column(name = "Last_name", nullable = false)
    @NotBlank(message = "Last name should not be empty")
    @Size(min = 3, max = 200, message = "Minimum last name length should be 3.")
    private String lastName;

    @Column(name = "age", nullable = false)
    @Min(5)
    @Max(127)
    private byte age;

    @Column
    @NotBlank(message = "Email should not be empty")
    @Email
    private String email;

    @Column(name = "login", nullable = false, unique = true)
    @NotBlank(message = "Login should not be empty")
    @Size(min = 3, max = 30, message = "Minimum login length should be 3.")
    private String login;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password should not be empty")
    @Size(min = 3, message = "Minimum password length should be 3.")
    private String password;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(String name, String lastName, byte age, String login, String password, Set<Role> roles, String email) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.login = login;
        this.password = password;
        this.roles = roles;
        this.email = email;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age;
    }
}
