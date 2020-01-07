package pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pack.model.Film;
import pack.model.Genre;
import pack.repository.FilmRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilmService {

    @Autowired
    private FilmRepo filmRepo;

    @Autowired
    private GenreService genreService;

    public List<Film> findAll() {
        return filmRepo.findAll();
    }

    public Film findById(Integer id){
        return filmRepo.findById(id).get();
    }

    public void save(Film film){
        filmRepo.save(film);
    }

    public int deleteFilm(Integer id) {
        if (filmRepo.findById(id).isPresent()) {
            filmRepo.deleteById(id);
            return 1;
        }
        return -1;
    }

    public Set<Genre> getGenres(Map<String,String> form){
        Iterable<Genre> genres = genreService.findAll();
        Set<Genre> tempGenres = new HashSet<>();
        for (String key:form.keySet()){
            for (Genre genre : genres){
                if (genre.getName().equals(key)){
                    tempGenres.add(genre);
                }
            }
        }
        return tempGenres;
    }

    public List<Film> findByDay(Integer day){
        return filmRepo.findByDay(day);
    }

    public List<Film> findByDayWithRating(Integer day, Integer limit){
        return filmRepo.findByDayWithRating(day, limit);
    }
}
