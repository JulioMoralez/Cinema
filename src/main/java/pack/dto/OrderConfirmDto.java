package pack.dto;

import java.util.Arrays;

public class OrderConfirmDto {
    private Integer userId;
    private Integer scheduleId;
    private int[][] places;

    public OrderConfirmDto() {
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

    public int[][] getPlaces() {
        return places;
    }

    public void setPlaces(int[][] places) {
        this.places = places;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "userId=" + userId +
                ", scheduleId=" + scheduleId +
                ", places=" + Arrays.toString(places) +
                '}';
    }
}
