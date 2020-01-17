package pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.dto.PlacesInHallDto;
import pack.model.Hall;
import pack.model.Message;
import pack.model.Place;
import pack.model.Schedule;
import pack.repository.ScheduleRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepo scheduleRepo;

    @Autowired
    private PlaceService placeService;

    private List<Integer> days = new ArrayList<>();
    private List<Integer> hours = new ArrayList<>();
    private List<Integer> mins = new ArrayList<>();

    public ScheduleService() {
        for (int i = 1; i <= 7; i++) {
            days.add(i);
        }
        for (int i = 10; i <= 23; i++) {
            hours.add(i);
        }
        for (int i = 0; i <= 55; i+=5) {
            mins.add(i);
        }
    }

    public List<Integer> getDays() {
        return days;
    }

    public List<Integer> getHours() {
        return hours;
    }

    public List<Integer> getMins() {
        return mins;
    }

    public void setDays(List<Integer> days) {
        this.days = days;
    }

    public Schedule findById(Integer id){
        return scheduleRepo.findById(id).get();
    }

    public List<Schedule> findAll() {
        return scheduleRepo.findAll();
    }

    public Schedule save(Schedule film){
        return scheduleRepo.save(film);
    }

    public Schedule delete(Integer id) {
        if (scheduleRepo.findById(id).isPresent()) {
            try {
                scheduleRepo.deleteById(id);
            }
            catch (Exception e){
                return new Schedule(-1);
            }
        }
        return new Schedule(id);
    }

    public List<Schedule> findByHallAndDate(Hall hall, LocalDate date){
        return scheduleRepo.findByHallAndDate(hall,date);
    }

    public List<Schedule> findByHall(Hall hall){
        return scheduleRepo.findByHall(hall);
    }

    public List<Schedule> findByDate(LocalDate date){
        return scheduleRepo.findByDate(date);
    }

    public List<Schedule> findByDateWeek(){
        LocalDate startDate = LocalDate.now();
        return scheduleRepo.findByDateWeek(startDate, startDate.plusDays(6));
    }

    public PlacesInHallDto fillHall(PlacesInHallDto placesInHallDto) {
        int[][] placesForSchedule = new int[placesInHallDto.getMaxRow()][placesInHallDto.getMaxPlace()];

        int authUser = placesInHallDto.getUserId();
        int price = 0;
        int ticketCount = 0;

        List<Place> places = placeService.findBySchedule(new Schedule(placesInHallDto.getScheduleId()));
        for (Place place : places) {
            if (placeService.resetBlockTime(place)) {
                placeService.save(place);
            }
            if (place.getOrder() == null) {
                if (!place.getStatus().equals(0)) {
                    if (place.getStatus().equals(authUser)) {
                        placesForSchedule[place.getRow()][place.getPlace()] = 1;
                        ticketCount++;
                    } else {
                        placesForSchedule[place.getRow()][place.getPlace()] = 2;
                    }
                }
            } else {
                placesForSchedule[place.getRow()][place.getPlace()] = -1;
            }

        }
        placesInHallDto.setPlacesForSchedule(placesForSchedule);
        return placesInHallDto;
    }

}
