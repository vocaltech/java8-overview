package fr.vocaltech.java8.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PositionPriority extends Position {
    private byte priority = 0;

    public PositionPriority(byte priority, double latitude, double longitude, long time, String trackId, String userId) {
        super(latitude, longitude, time, trackId, userId);
        this.priority = priority;
    }
}
