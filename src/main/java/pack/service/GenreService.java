package pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.model.Genre;
import pack.repository.FilmRepo;
import pack.repository.GenreRepo;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepo genreRepo;

    public Genre findById(Integer id){
        return genreRepo.findById(id).get();
    }

    public List<Genre> findAll() {
        return genreRepo.findAll();
    }

    public Genre save(Genre film){
        return genreRepo.save(film);
    }

    public Genre delete(Integer id) {
        if (genreRepo.findById(id).isPresent()) {
            try {
                genreRepo.deleteById(id);
            }
            catch (Exception e){
                return new Genre(-1);
            }
        }
        return new Genre(id);
    }

}
