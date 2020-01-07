package pack.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pack.model.Film;
import pack.model.Genre;

import java.util.List;

@Repository
public interface FilmRepo extends CrudRepository<Film, Integer> {
    List<Film> findByYear(Integer year);
    List<Film> findByGenres(Genre genre);
    List<Film> findAll();

    @Query("select ft from Film ft where ft.id in (select sc.film from Schedule sc where sc.day=:day group by sc.film) order by ft.rating desc")
    List<Film> findByDay(@Param("day") Integer day);

    @Query(nativeQuery = true, value = "select ft.id from t_film ft where ft.id in (select sc.film_id from t_schedule sc where sc.day=:day group by sc.film_id) order by ft.rating desc limit :limit")
    List<Film> findByDayWithRating(@Param("day") Integer day, @Param("limit") Integer limit);
}
