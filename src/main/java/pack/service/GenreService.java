package pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.model.Film;
import pack.model.Genre;
import pack.repository.FilmRepo;
import pack.repository.GenreRepo;

@Service
public class GenreService {

    @Autowired
    private GenreRepo genreRepo;

    public Iterable<Genre> findAll() {
        return genreRepo.findAll();
    }

}
