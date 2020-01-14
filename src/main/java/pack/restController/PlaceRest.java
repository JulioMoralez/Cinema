package pack.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pack.model.Place;
import pack.service.PlaceService;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PlaceRest {

    @Autowired
    private PlaceService placeService;

    static final String URL = "/place";

    @RequestMapping(value = URL + "/{entity}", method = RequestMethod.GET, produces = "application/json")
    public Place find(@PathVariable Place entity){
        return entity;
    }

    @RequestMapping(value = URL + "s", method = RequestMethod.GET, produces = "application/json")
    public List<Place> findAll() {
        return placeService.findAll();
    }

    @RequestMapping(value = URL, method = RequestMethod.POST, consumes = "application/json")
    public Place save(@RequestBody Place entity){
        return placeService.save(entity);
    }

    @RequestMapping(value = URL + "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public Place delete(@PathVariable Integer id){
        return placeService.delete(id);
    }
}

