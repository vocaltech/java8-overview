package fr.vocaltech.java8.streams;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class StreamsTest {
    List<String> list;

    @BeforeEach
    void beforeEach() {
        list = new ArrayList<>();
        list.add("elt1");
    }
    @Test
    void givenList_whenCreateStream_thenReturnStream() throws ClassNotFoundException {
        assertThat(list.stream()).isInstanceOf(Class.forName("java.util.stream.Stream"));
    }

    @Test
    void givenList_whenAddOneElement_thenSizeIsOne() {
        assertThat(list.stream().distinct().count()).isEqualTo(1);
    }

    @Test
    void givenList_whenIterating_thenReturnTrue() {
        boolean isExist = list.stream()
                .anyMatch(l -> l.contains("elt1"));

        assertThat(isExist).isTrue();
    }
}
