package pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pack.dto.UserDto;
import pack.model.Genre;
import pack.model.Role;
import pack.model.User;
import pack.repository.RoleRepo;
import pack.repository.UserRepo;

import java.util.*;

import static pack.config.Consts.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findById(Integer id) {
        return userRepo.findById(id).orElse(null);
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }

        user.setActive(true);
        user.setEmailConfirmed(false);
        user.setRoles(Collections.singleton(ROLE_USER));
        if (user.getUsername().equals("admin")){
            Set<Role> roles = new HashSet<>();
            roles.add(ROLE_USER);
            roles.add(ROLE_MODERATOR);
            roles.add(ROLE_ADMIN);
            user.setRoles(roles);   // !!! для теста даём полные права пользователю с ником admin
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }

    public UserDto saveUser(UserDto userDto) {
        User user = userRepo.findByUsername(userDto.getUsername());
        if (user != null) {
            return null;
        }
        user = new User();
        user.setUsername(userDto.getUsername());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        user.setActive(true);
        user.setRoles(Collections.singleton(ROLE_USER));
        if (user.getUsername().equals("admin")){
            Set<Role> roles = new HashSet<>();
            roles.add(ROLE_USER);
            roles.add(ROLE_MODERATOR);
            roles.add(ROLE_ADMIN);
            user.setRoles(roles);   // !!! для теста даём полные права пользователю с ником admin
        }
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        return new UserDto(userRepo.save(user));
    }



    public int deleteUser(Integer id) {
        if (userRepo.findById(id).isPresent()) {
            userRepo.deleteById(id);
            return 1;
        }
        return -1;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public boolean isAdmin(User user){
        return user.getRoles().contains(ROLE_ADMIN);
    }

    public boolean isModerator(User user){
        return ((user.getRoles().contains(ROLE_ADMIN)) || (user.getRoles().contains(ROLE_MODERATOR)));
    }

    public Set<Role> getRoles(Map<String,String> form){
        Set<Role> tempRoles = new HashSet<>();
        for (String key:form.keySet()){
            for (Role role : ROLES){
                if (role.getName().equals(key)){
                    tempRoles.add(role);
                }
            }
        }
        return tempRoles;
    }

    public Set<Role> getRoles(List<Role> roles){
        Set<Role> tempRoles = new HashSet<>();
        for (Role role: roles) {
            if (role.isCheck()) {
                tempRoles.add(role);
            }
        }
        return tempRoles;
    }

    public boolean updateUser(User user) {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if ((userFromDB != null) && !(user.getId().equals(userFromDB.getId()))) {
            return false;
        }

        userRepo.save(user);
        return true;
    }

    public void updateUser(UserDto userDto) {
        User userFromDB = userRepo.findById(userDto.getId()).orElse(null);
        if (userFromDB!=null){
            userFromDB.setName(userDto.getName());
            userFromDB.setEmail(userDto.getEmail());
            userFromDB.setRoles(getRoles(userDto.getRoles()));
            userRepo.save(userFromDB);
        }
    }
}
