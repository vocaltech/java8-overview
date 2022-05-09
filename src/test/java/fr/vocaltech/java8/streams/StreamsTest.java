package fr.vocaltech.java8.streams;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class StreamsTest {
    @Test
    void givenList_whenCreateStream_returnStream() throws ClassNotFoundException {
        List<String> list = new ArrayList<>();
        list.add("a");

        assertThat(list.stream()).isInstanceOf(Class.forName("java.util.stream.Stream"));
    }

}
