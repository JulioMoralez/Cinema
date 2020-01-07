package pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.model.Place;
import pack.model.Schedule;
import pack.repository.PlaceRepo;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepo placeRepo;

    public void save(Place place){
        placeRepo.save(place);
    }

    public Place findByScheduleAndRowAndPlace(Schedule schedule, Integer rowValue, Integer placeValue){
        return placeRepo.findByScheduleAndRowAndPlace(schedule, rowValue, placeValue).stream().findFirst().orElse(null);
    }

    public List<Place> findBySchedule(Schedule schedule){
        return placeRepo.findBySchedule(schedule);
    }
}
