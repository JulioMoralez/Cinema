package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pack.model.Hall;
import pack.model.Place;
import pack.model.Schedule;
import pack.model.User;
import pack.service.HallService;
import pack.service.PlaceService;
import pack.service.ScheduleService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;


public class HallController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private HallService hallService;

    @Autowired
    private PlaceService placeService;

    @GetMapping("/hall/{schedule}")
    public String schedule(@AuthenticationPrincipal User authUser, @PathVariable("schedule") Schedule schedule, Model model){
        model.addAttribute("schedule",schedule);

        Hall hall = schedule.getHall();
        int[][] placesForForm = new int[hall.getRow()][hall.getPlace()];

        int price=0;
        int ticketCount=0;
        List<Place> places = placeService.findBySchedule(schedule);
        for (Place place:places){
            if (placeService.resetBlockTime(place)){
                placeService.save(place);
            }
            if(place.getOrder()==null){
                if(!place.getStatus().equals(0)){
                    if (place.getStatus().equals(authUser.getId())){
                        placesForForm[place.getRow()-1][place.getPlace()-1]=1;
                        ticketCount++;
                    }
                    else {
                        placesForForm[place.getRow()-1][place.getPlace()-1]=2;
                    }
                }
            }
            else {
                placesForForm[place.getRow()-1][place.getPlace()-1]=-1;
            }

        }
        model.addAttribute("places",placeService.findBySchedule(schedule));
        model.addAttribute("placesForForm",placesForForm);
        model.addAttribute("ticketCount",ticketCount);
        return "hall";
    }

    @GetMapping("/hall/placeSelect/{value}")
    public String placeSelect(@AuthenticationPrincipal User authUser, @PathVariable("value") String value){
        String[] s = value.split("x");
        Schedule schedule=null;
        int rowValue = -1;
        int placeValue = -1;
        if (s.length==3){
            schedule=scheduleService.findById(Integer.parseInt(s[0]));
            rowValue=Integer.parseInt(s[1]);
            placeValue=Integer.parseInt(s[2]);
            Place place = placeService.findByScheduleAndRowAndPlace(schedule,rowValue,placeValue);
            if (place==null){
                placeService.save(new Place(schedule,rowValue,placeValue,authUser.getId(), LocalDateTime.now()));
            }
            else {
                if (place.getStatus().equals(authUser.getId())){
                    place.setBlockTime(null);
                    place.setStatus(0);
                    placeService.save(place);
                }
                else if((place.getStatus().equals(0)) && (place.getOrder()==null)){
                    place.setBlockTime(LocalDateTime.now());
                    place.setStatus(authUser.getId());
                    placeService.save(place);
                }
            }
        }
        if (schedule != null) {
            return "redirect:/hall/"+schedule.getId();
        }
        return "redirect:/schedule";

    }
}
