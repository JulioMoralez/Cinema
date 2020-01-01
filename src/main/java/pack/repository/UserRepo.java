package pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pack.model.User;

public interface UserRepo extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
