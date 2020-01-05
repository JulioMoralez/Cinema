package pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pack.model.User;
import pack.repository.RoleRepo;
import pack.repository.UserRepo;

import java.util.Collections;

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

    public User read(Integer id) {
        return userRepo.findById(id).orElse(null);
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(ROLE_USER));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }

    public boolean updateUser(User user) {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if ((userFromDB != null) && !(user.getId().equals(userFromDB.getId()))) {
            return false;
        }

        userRepo.save(user);
        return true;
    }

    public int deleteUser(Integer id) {
        if (userRepo.findById(id).isPresent()) {
            userRepo.deleteById(id);
            return 1;
        }
        return -1;
    }

    public Iterable<User> readAll() {
        return userRepo.findAll();
    }

    public boolean isAdmin(User user){
        return user.getRoles().contains(ROLE_ADMIN);
    }

    public boolean isModerator(User user){
        return ((user.getRoles().contains(ROLE_ADMIN)) || (user.getRoles().contains(ROLE_MODERATOR)));
    }

}
