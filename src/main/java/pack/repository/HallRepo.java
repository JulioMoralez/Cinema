package pack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pack.model.Film;
import pack.model.Hall;

import java.util.List;

@Repository
public interface HallRepo extends CrudRepository<Hall, Integer> {
    List<Hall> findAll();
}
