package pack.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pack.model.Film;
import pack.service.FilmService;

import java.util.List;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FilmRest {

    @Autowired
    private FilmService filmService;

    @RequestMapping(value = "/film/{film}", method = RequestMethod.GET, produces = "application/json")
    public Film find(@PathVariable Film film){
        return film;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET, produces = "application/json")
    public List<Film> findAll() {
        return filmService.findAll();
    }

    @RequestMapping(value = "/film", method = RequestMethod.POST, consumes = "application/json")
    public Film save(@RequestBody Film film){
        return filmService.save(film);
    }

    @RequestMapping(value = "/film/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public Film delete(@PathVariable Integer id){
        return filmService.delete(id);
    }



//    @RequestMapping(value = "/film/{id}", method = RequestMethod.POST, consumes = "application/json")
//    public Film update(@PathVariable Integer id, @RequestBody Film film){
//        return filmService.save(film);
//    }
}

