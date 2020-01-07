package pack.model;

import javax.persistence.*;

@Entity
@Table(name = "t_schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Film film;

    @ManyToOne(fetch = FetchType.EAGER)
    private Hall hall;

    private Integer day;
    private Integer time;

    public Schedule() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", film=" + film +
                ", hall=" + hall +
                ", day=" + day +
                ", time=" + time +
                '}';
    }
}
