package pack.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer row;
    private Integer place;
    private Integer status;
    private LocalDateTime blockTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    public Place() {
    }

    public Place(Schedule schedule, Integer row, Integer place, Integer status, LocalDateTime blockTime) {
        this.schedule = schedule;
        this.row = row;
        this.place = place;
        this.status = status;
        this.blockTime = blockTime;
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

    public LocalDateTime getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(LocalDateTime blockTime) {
        this.blockTime = blockTime;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
