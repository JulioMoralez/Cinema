package pack.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pack.model.Hall;
import pack.service.HallService;

import java.util.List;


@CrossOrigin
@RestController
public class HallsRest {

    @Autowired
    private HallService hallService;

    static final String URL = "/hall";

    @RequestMapping(value = URL + "/{entity}", method = RequestMethod.GET, produces = "application/json")
    public Hall find(@PathVariable Hall entity){
        return entity;
    }

    @RequestMapping(value = URL + "s", method = RequestMethod.GET, produces = "application/json")
    public List<Hall> findAll() {
        return hallService.findAll();
    }

    @RequestMapping(value = URL, method = RequestMethod.POST, consumes = "application/json")
    public Hall save(@RequestBody Hall entity){
        return hallService.save(entity);
    }

    @RequestMapping(value = URL + "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public Hall delete(@PathVariable Integer id){
        return hallService.delete(id);
    }
}

