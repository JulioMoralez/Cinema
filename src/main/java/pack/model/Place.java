package pack.model;

import javax.persistence.*;

@Entity
@Table(name = "t_place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer row;
    private Integer place;
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Schedule schedule;

    public Place() {
    }

    public Place(Schedule schedule, Integer row, Integer place, Integer status) {
        this.schedule = schedule;
        this.row = row;
        this.place = place;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", row=" + row +
                ", place=" + place +
                ", status=" + status +
                ", schedule=" + schedule +
                '}';
    }
}
