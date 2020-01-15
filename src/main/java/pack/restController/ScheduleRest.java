package pack.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pack.model.Schedule;
import pack.service.ScheduleService;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ScheduleRest {

    @Autowired
    private ScheduleService scheduleService;


    static final String URL = "/schedule";

    @RequestMapping(value = URL + "/{entity}", method = RequestMethod.GET, produces = "application/json")
    public Schedule find(@PathVariable Schedule entity){
        return entity;
    }

    @RequestMapping(value = URL + "s", method = RequestMethod.GET, produces = "application/json")
    public List<Schedule> findAll() {
        return scheduleService.findAll();
    }

    @RequestMapping(value = URL, method = RequestMethod.POST, consumes = "application/json")
    public Schedule save(@RequestBody Schedule entity){
        return scheduleService.save(entity);
    }

    @RequestMapping(value = URL + "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public Schedule delete(@PathVariable Integer id){
        return scheduleService.delete(id);
    }

    //==========================

    @RequestMapping(value = URL + "s/week", method = RequestMethod.GET, produces = "application/json")
    public List<Schedule> findByDateWeek() {
        return scheduleService.findByDateWeek();
    }
}

