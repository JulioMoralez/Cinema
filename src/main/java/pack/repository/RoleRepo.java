package pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pack.model.Role;

public interface RoleRepo extends CrudRepository<Role, Integer> {
}
