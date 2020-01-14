package pack.model;

import javax.persistence.*;
import java.time.LocalDate;

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

    private Integer time;
    private LocalDate date;

    private Integer price;

    public Schedule() {
    }

    public Schedule(Integer id) {
        this.id = id;
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


    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", film=" + film +
                ", hall=" + hall +
                ", time=" + time +
                '}';
    }
}
