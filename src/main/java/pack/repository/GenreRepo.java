package pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pack.model.Genre;

import java.util.List;

@Repository
public interface GenreRepo extends CrudRepository<Genre, Integer> {
    List<Genre> findByName(String name);
}
