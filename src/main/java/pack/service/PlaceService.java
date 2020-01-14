package pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pack.model.Place;
import pack.model.Schedule;
import pack.repository.PlaceRepo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlaceService {

    @Value("${blockTime}")
    private int BLOCK_TIME;

    @Autowired
    private PlaceRepo placeRepo;

    public Place findById(Integer id){
        return placeRepo.findById(id).get();
    }

    public List<Place> findAll() {
        return placeRepo.findAll();
    }

    public Place save(Place film){
        return placeRepo.save(film);
    }

    public Place delete(Integer id) {
        if (placeRepo.findById(id).isPresent()) {
            try {
                placeRepo.deleteById(id);
            }
            catch (Exception e){
                return new Place(-1);
            }
        }
        return new Place(id);
    }

    public Place findByScheduleAndRowAndPlace(Schedule schedule, Integer rowValue, Integer placeValue){
        return placeRepo.findByScheduleAndRowAndPlace(schedule, rowValue, placeValue).stream().findFirst().orElse(null);
    }

    public List<Place> findBySchedule(Schedule schedule){
        return placeRepo.findBySchedule(schedule);
    }

    public List<Place> findByScheduleAndStatus(Schedule schedule, Integer status){
        return placeRepo.findByScheduleAndStatus(schedule,status);
    }

    public boolean resetBlockTime(Place place){
        if (place.getBlockTime()!=null){
            Duration duration = Duration.between(place.getBlockTime(),LocalDateTime.now());
            if (duration.toMillis()>BLOCK_TIME){
                place.setBlockTime(null);
                place.setStatus(0);
                return true;
            }
        }
        return false;
    }
}
