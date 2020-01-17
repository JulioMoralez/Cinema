package pack.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pack.dto.PlacesInHallDto;
import pack.model.Hall;
import pack.model.Place;
import pack.model.Schedule;
import pack.model.User;
import pack.service.PlaceService;
import pack.service.ScheduleService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ScheduleRest {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private PlaceService placeService;


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

    @RequestMapping(value = URL + "/placelist/", method = RequestMethod.POST, consumes = "application/json")
    public PlacesInHallDto placelist(@RequestBody PlacesInHallDto placesInHallDto){
//        model.addAttribute("places",placeService.findBySchedule(schedule));
//        model.addAttribute("placesForSchedule",placesForSchedule);
//        model.addAttribute("ticketCount",ticketCount);
        return scheduleService.fillHall(placesInHallDto);
    }

    @RequestMapping(value = URL + "/placeselect", method = RequestMethod.POST, consumes = "application/json")
    public PlacesInHallDto placeSelect(@RequestBody PlacesInHallDto placesInHallDto){
        Schedule schedule=scheduleService.findById(placesInHallDto.getScheduleId());
        int rowValue = placesInHallDto.getRow();
        int placeValue = placesInHallDto.getPlace();
        int authUser = placesInHallDto.getUserId();
        Place place = placeService.findByScheduleAndRowAndPlace(schedule,rowValue,placeValue);
        if (place==null){
            placeService.save(new Place(schedule,rowValue,placeValue,authUser, LocalDateTime.now()));
        }
        else {
            if (place.getStatus().equals(authUser)){
                place.setBlockTime(null);
                place.setStatus(0);
                placeService.save(place);
            }
            else if((place.getStatus().equals(0)) && (place.getOrder()==null)){
                place.setBlockTime(LocalDateTime.now());
                place.setStatus(authUser);
                placeService.save(place);
            }
        }
        return scheduleService.fillHall(placesInHallDto);
    }
}

