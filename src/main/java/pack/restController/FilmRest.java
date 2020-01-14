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

    static final String URL = "/film";

    @RequestMapping(value = URL + "/{entity}", method = RequestMethod.GET, produces = "application/json")
    public Film find(@PathVariable Film entity){
        return entity;
    }

    @RequestMapping(value = URL + "s", method = RequestMethod.GET, produces = "application/json")
    public List<Film> findAll() {
        return filmService.findAll();
    }

    @RequestMapping(value = URL, method = RequestMethod.POST, consumes = "application/json")
    public Film save(@RequestBody Film entity){
        return filmService.save(entity);
    }

    @RequestMapping(value = URL + "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public Film delete(@PathVariable Integer id){
        return filmService.delete(id);
    }
}

