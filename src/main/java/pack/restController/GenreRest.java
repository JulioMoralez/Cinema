package pack.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pack.model.Genre;
import pack.service.GenreService;

import java.util.List;


@CrossOrigin
@RestController
public class GenreRest {

    @Autowired
    private GenreService genreService;

    static final String URL = "/genre";

    @RequestMapping(value = URL + "/{entity}", method = RequestMethod.GET, produces = "application/json")
    public Genre find(@PathVariable Genre entity){
        return entity;
    }

    @RequestMapping(value = URL + "s", method = RequestMethod.GET, produces = "application/json")
    public List<Genre> findAll() {
        return genreService.findAll();
    }

    @RequestMapping(value = URL, method = RequestMethod.POST, consumes = "application/json")
    public Genre save(@RequestBody Genre entity){
        return genreService.save(entity);
    }

    @RequestMapping(value = URL + "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public Genre delete(@PathVariable Integer id){
        return genreService.delete(id);
    }
}

