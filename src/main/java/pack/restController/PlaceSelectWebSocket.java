package pack.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import pack.dto.PlacesInHallDto;
import pack.model.Place;
import pack.model.Schedule;
import pack.service.PlaceService;
import pack.service.ScheduleService;

import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PlaceSelectWebSocket {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private PlaceService placeService;

    @MessageMapping("/placeselectws")
    @SendTo("/topic/placeselectws")
    public PlacesInHallDto placeSelect(PlacesInHallDto placesInHallDto){
        if ((placesInHallDto.getPlace()!=-1) && (placesInHallDto.getUserId()!=-1)){
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
        }
        return scheduleService.fillHall(placesInHallDto);
    }
}