package fr.vocaltech.java8.models;

public class PositionPriority extends Position {
    private byte priority = 0;

    public PositionPriority(byte priority, double latitude, double longitude, long time, String trackId, String userId) {
        super(latitude, longitude, time, trackId, userId);
        this.priority = priority;
    }
}
