package pack.dto;

import pack.model.Role;
import pack.model.User;

import java.util.*;

import static pack.config.Consts.*;


public class UserDto {

    private Integer id;
    private String username;
    private String name;
    private String password;
    private List<Role> roles = new ArrayList<>();
    private String email;
    private Boolean emailConfirmed;
    private String verifyCode;

    public UserDto() {
    }

    public UserDto(Integer id) {
        this.id = id;
    }

        public UserDto(User user){
            this.id = user.getId();
            this.username = user.getUsername();
            this.name = user.getName();
            this.email = user.getEmail();
            this.emailConfirmed = user.isEmailConfirmed();
            this.roles.add(new Role(1,"ROLE_USER", "Пользователь"));
            this.roles.add(new Role(2,"ROLE_ADMIN", "Администратор"));
            this.roles.add(new Role(3,"ROLE_MODERATOR", "Модератор"));
            for (Role role: this.roles)
                if (user.getRoles().contains(role)){
                    role.setCheck(true);
                }
                else {
                    role.setCheck(false);
                }
        }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(Boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
