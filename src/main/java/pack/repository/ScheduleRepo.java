package pack.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pack.model.Film;
import pack.model.Hall;
import pack.model.Schedule;

import java.util.List;

@Repository
public interface ScheduleRepo extends CrudRepository<Schedule, Integer> {
    List<Schedule> findAll();
    List<Schedule> findByHallAndDay(Hall hall, Integer day);
    List<Schedule> findByHall(Hall hall);

    @Query("select s from Schedule s where s.day=:day order by s.time")
    List<Schedule> findByDay(@Param("day") Integer day);
}
