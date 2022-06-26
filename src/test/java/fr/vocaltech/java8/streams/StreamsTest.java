package fr.vocaltech.java8.streams;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.vocaltech.java8.models.CartItem;
import org.assertj.core.data.Offset;
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

        // stream pipeline
        Stream<String> items2 = Stream.of("i2", "i1", "i3");
        Stream<String> sortedItems2 = items2.sorted();
        assertThat(sortedItems2.collect(Collectors.toList())).containsExactly("i1", "i2", "i3");
    }

    @Test
    void createStream_thenReturnMax() {
        List<Integer> ints = new ArrayList<>();
        ints.add(5);
        ints.add(3);
        ints.add(2);
        ints.add(7);

        Optional<Integer> optionalMax = ints.stream().max(Integer::compare);
        Integer maxVal = optionalMax.get();
        assertThat(maxVal).isEqualTo(7);
    }

    @Test
    void createStream_thenReturnMin() {
        List<Integer> ints = Arrays.asList(5, 3, 2, 7);
        Optional<Integer> minOptional = ints.stream().min(Integer::compare);

        assertThat(minOptional.get()).isEqualTo(2);
    }

    @Test
    void createStream_thenReduce() {
        OptionalInt optionalRes = IntStream.range(1, 3).reduce((a, b) -> a + b);
        assertThat(optionalRes.getAsInt()).isEqualTo(3);

        int res = IntStream.range(1, 3).reduce(5, Integer::sum);
        assertThat(res).isEqualTo(8);

        int reducedParams = Stream.of(1, 2, 3)
                .reduce(10, (a, b) -> a + b, (a, b) -> {
            System.out.println("combiner was called...");
            return a + b;
        });

        assertThat(reducedParams).isEqualTo(16);
    }

    @Test
    void createStream_thenCollect() {
        List<CartItem> items = new ArrayList<>();
        items.add(new CartItem("item1", "cat1", 100.25));
        items.add(new CartItem("item2", "cat2", 140.15));
        items.add(new CartItem("item3", "cat2", 317.25));

        // get items descriptions as list
        List<String> descriptionsAsList = items.stream()
                .map(CartItem::getDescription)
                .collect(Collectors.toList());

        assertThat(descriptionsAsList).containsExactly("item1", "item2", "item3");

        // get items descriptions as string
        String descriptionsAsString = items.stream()
                .map(CartItem::getDescription)
                .collect(Collectors.joining(", ", "[", "]"));

        assertThat(descriptionsAsString).isEqualTo("[item1, item2, item3]");

        // get average price
        double averagePrice = items.stream()
                .collect(Collectors.averagingDouble(CartItem::getPrice));

        assertThat(averagePrice).isCloseTo(185.0, Offset.offset(0.9));

        // process the sum of the stream
        double sumPrice = items.stream()
                .collect(Collectors.summingDouble(CartItem::getPrice));

        assertThat(sumPrice).isEqualTo(557.65);

        // collect statistical information about stream's elements
        DoubleSummaryStatistics doubleSummaryStats = items.stream()
                .collect(Collectors.summarizingDouble(CartItem::getPrice));

        assertThat(doubleSummaryStats.getCount()).isEqualTo(3);

        // grouping of stream's elements
         Map<Double, List<CartItem>> itemsByPrice = items.stream()
                .collect(Collectors.groupingBy(CartItem::getPrice));

        CartItem item = (CartItem) itemsByPrice.get(317.25).get(0);
        assertThat(item.getDescription()).isEqualTo("item3");

        item = (CartItem) itemsByPrice.get(100.25).get(0);
        assertThat(item.getDescription()).isEqualTo("item1");

        // dividing stream's elements
        Map<Boolean, List<CartItem>> itemsPartitionedByPrice = items.stream()
                .collect(Collectors.partitioningBy(elt -> elt.getPrice() > 140.0));

        CartItem itemTrue = (CartItem) itemsPartitionedByPrice.get(true).get(0);
        assertThat(itemTrue.getDescription()).isEqualTo("item2");

        itemTrue = (CartItem) itemsPartitionedByPrice.get(true).get(1);
        assertThat(itemTrue.getDescription()).isEqualTo("item3");
    }
}
