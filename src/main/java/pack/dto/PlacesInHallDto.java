package pack.dto;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;

public class PlacesInHallDto {
    private Integer userId;
    private Integer scheduleId;
    private Integer row;
    private Integer place;
    private Integer maxRow;
    private Integer maxPlace;
    private int[][] placesForSchedule;

    public PlacesInHallDto() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
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

    public int[][] getPlacesForSchedule() {
        return placesForSchedule;
    }

    public void setPlacesForSchedule(int[][] placesForSchedule) {
        this.placesForSchedule = placesForSchedule;
    }

    public Integer getMaxRow() {
        return maxRow;
    }

    public void setMaxRow(Integer maxRow) {
        this.maxRow = maxRow;
    }

    public Integer getMaxPlace() {
        return maxPlace;
    }

    public void setMaxPlace(Integer maxPlace) {
        this.maxPlace = maxPlace;
    }

    @Override
    public String toString() {
        return "PlacesInHallDto{" +
                "userId=" + userId +
                ", scheduleId=" + scheduleId +
                ", row=" + row +
                ", place=" + place +
                ", maxRow=" + maxRow +
                ", maxPlace=" + maxPlace +
                ", placesForSchedule=" + Arrays.toString(placesForSchedule) +
                '}';
    }
}
