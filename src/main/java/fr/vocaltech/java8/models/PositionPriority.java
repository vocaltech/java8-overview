package fr.vocaltech.java8.models;

import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@Data
@ToString(callSuper = true)
public class PositionPriority extends Position
        implements Comparable<PositionPriority> {
    private byte priority = 0;

    public PositionPriority(byte priority, double latitude, double longitude, long time, String trackId, String userId) {
        super(latitude, longitude, time, trackId, userId);
        this.priority = priority;
    }

    @Override
    public int compareTo(PositionPriority o) {
        if (priority > o.priority) {
            return 1;
        } else if (priority < o.priority) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PositionPriority that = (PositionPriority) o;
        return priority == that.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), priority);
    }
}
