package pack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pack.model.Film;
import pack.model.Genre;

import java.util.List;

@Repository
public interface FilmRepo extends CrudRepository<Film, Integer> {
    List<Film> findByYear(Integer year);
    List<Film> findByGenres(Genre genre);
    List<Film> findAll();
}
