package fr.vocaltech.java8.collections;

import java.time.Instant;
import java.util.Objects;
import java.util.PriorityQueue;

import fr.vocaltech.java8.models.PositionPriority;

import lombok.var;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class PositionPriorityQueueTest {
    @Test
    void test_createPositionPriorityQueue() {
        // init Queue
        var queue = new PriorityQueue<PositionPriority>();

        // enqueue one value with priority 1
        byte priority1 = 1;
        var p1 = new PositionPriority(priority1, 42.3, 1.2, Instant.now().toEpochMilli(), "trackId", "userId");
        queue.offer(p1);

        // enqueue some values with priority 2
        byte priority2 = 2;
        var p2 = new PositionPriority(priority2, 45.1, 1.4, Instant.now().toEpochMilli(), "trackId", "userId");
        queue.offer(p2);

        var p3 = new PositionPriority(priority2, 44.1, 1.3, Instant.now().toEpochMilli(), "trackId", "userId");
        queue.offer(p3);

        // enqueue some values with priority 3
        byte priority3 = 3;
        var p4 = new PositionPriority(priority3, 40.7, 1.5, Instant.now().toEpochMilli(), "trackId", "userId");
        queue.offer(p4);

        // assertions
        assertThat(Objects.requireNonNull(queue.poll()).getPriority()).isEqualTo((byte) 1);
        assertThat(Objects.requireNonNull(queue.poll()).getPriority()).isEqualTo((byte) 2);
        assertThat(Objects.requireNonNull(queue.poll()).getPriority()).isEqualTo((byte) 2);
        assertThat(Objects.requireNonNull(queue.poll()).getPriority()).isEqualTo((byte) 3);
    }
}