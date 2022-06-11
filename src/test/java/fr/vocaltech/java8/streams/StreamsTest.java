package fr.vocaltech.java8.streams;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

    @Test
    void createStream_withGenerate_thenReturnList() {
        Random random = new Random();
        List<Integer> list = Stream
                .generate(() -> random.nextInt(11))
                .limit(10)
                .collect(Collectors.toList());

        assertThat(list).containsAnyOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    void createStream_withIterate_thenReturnList() {
        List<Integer> list = Stream
                .iterate(0, n -> n + 1)
                .limit(11)
                .collect(Collectors.toList());

        assertThat(list).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    void createStream_withIntStream_thenReturnList() {
        IntStream intStream = IntStream.range(0, 11);
        List<Integer> list = intStream
                .boxed()
                .collect(Collectors.toList());

        assertThat(list).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    void createStream_withFile_thenReturnLines() throws IOException {
        String curDir = Paths.get("")
                .toAbsolutePath()
                .toString();

        Path path = Paths.get(curDir, "stream.txt");

        // create new file
        try {
            Files.createFile(path);
        } catch(FileAlreadyExistsException exc) {
            assertThat(exc.toString()).contains("FileAlreadyExistsException");
        }

        // write some lines
        List<String> lines = new ArrayList<>();
        lines.add("line1");
        lines.add("line2");
        lines.add("line3");

        Files.write(path, lines, StandardOpenOption.APPEND);

        // read file as stream
        try (Stream<String> streamLines = Files.lines(path)) {
            streamLines.forEach(System.out::println);
        }
    }

    @Test
    void createStreamPipeline() {
        Stream<String> items = Stream.of("item1", "item2", "item3");

        Stream<String> skipItems = items.skip(1);
        assertThat(skipItems.collect(Collectors.toList())).containsExactly("item2", "item3");
    }
}
