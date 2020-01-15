package pack.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pack.model.Film;
import pack.model.Hall;
import pack.model.Schedule;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepo extends CrudRepository<Schedule, Integer> {
    List<Schedule> findAll();
    List<Schedule> findByHallAndDate(Hall hall, LocalDate date);
    List<Schedule> findByHall(Hall hall);

    @Query("select s from Schedule s where s.date=:date order by s.time")
    List<Schedule> findByDate(@Param("date") LocalDate date);

    @Query("select s from Schedule s where s.date BETWEEN :startDate AND :endDate order by s.time")
    List<Schedule> findByDateWeek(@Param("startDate") LocalDate startDate, @Param("endDate")LocalDate endDate);
}
