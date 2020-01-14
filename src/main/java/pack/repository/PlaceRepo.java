package pack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pack.model.Film;
import pack.model.Place;
import pack.model.Schedule;

import java.util.List;

@Repository
public interface PlaceRepo extends CrudRepository<Place, Integer> {
    List<Place> findByScheduleAndRowAndPlace(Schedule schedule, Integer rowValue, Integer placeValue);
    List<Place> findBySchedule(Schedule schedule);
    List<Place> findByScheduleAndStatus(Schedule schedule, Integer status);
    List<Place> findAll();

}
