package pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.model.Film;
import pack.model.Message;
import pack.repository.FilmRepo;

@Service
public class FilmService {

    @Autowired
    private FilmRepo filmRepo;

    public Iterable<Film> findAll() {
        return filmRepo.findAll();
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
}
