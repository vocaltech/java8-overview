package fr.vocaltech.java8.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Position {
    private double latitude;
    private double longitude;
    private long time;
    private String trackId;
    private String userId;

    public Position(double latitude, double longitude, long time, String trackId, String userId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.trackId = trackId;
        this.userId = userId;
    }
}
