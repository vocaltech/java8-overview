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
}
