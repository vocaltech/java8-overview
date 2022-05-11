package fr.vocaltech.java8.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
    void givenList_whenFiltering_thenReturnFilteredStream() {
        list.add("elt2");
        list.add("no_1");
        list.add("elt3");
        list.add("no_2");

        Stream<String> filteredStream = list.stream().filter(e -> e.contains(("elt")));

        List<String> filteredList = filteredStream.collect(Collectors.toList());
        assertThat(filteredList).containsExactly("elt1", "elt2", "elt3");
    }

    @Test
    void givenList_whenMapping_thenReturnAlteredListContent() {
        List<String> alteredList = list
                .stream()
                .map(e -> "altered_" + e)
                .collect(Collectors.toList());

        assertThat(alteredList).containsExactly("altered_elt1");
    }

    @Test
    void givenList_whenReducing_thenReturnSumOfList() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);

        Integer reducedVal = integers
                .stream()
                .reduce(0, Integer::sum);

        assertThat(reducedVal).isEqualTo(6);
    }

    @Test
    void createStream_withBuilder_thenReturnStream() throws ClassNotFoundException {
        Stream<String> streamBuilder = Stream.<String>builder()
                .add("builder1")
                .add("builder2")
                .add("builder3")
                .build();

        assertThat(streamBuilder).isInstanceOf(Class.forName("java.util.stream.Stream"));

        List<String> streamList = streamBuilder.collect(Collectors.toList());
        assertThat(streamList.get(0)).isEqualTo("builder1");
    }
}
