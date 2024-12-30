import java.util.*;
import java.util.stream.*;
import java.io.*;

public class StreamOperationsDemo {
    public static void main(String[] args) throws IOException {
        // 1. Source Operations
        // Stream.of
        Stream<Integer> streamOf = Stream.of(1, 2, 3, 4, 5);
        System.out.println("Stream.of: " + streamOf.collect(Collectors.toList()));

        // Arrays.stream
        int[] intArray = {6, 7, 8, 9, 10};
        IntStream arrayStream = Arrays.stream(intArray);
        System.out.println("Arrays.stream: " + arrayStream.boxed().collect(Collectors.toList()));

        // Collection.stream
        List<String> list = List.of("Apple", "Banana", "Cherry");
        Stream<String> listStream = list.stream();
        System.out.println("Collection.stream: " + listStream.collect(Collectors.toList()));

        // Collection.parallelStream
        Stream<String> parallelStream = list.parallelStream();
        System.out.println("Collection.parallelStream: " + parallelStream.collect(Collectors.toList()));

        // Stream.generate
        Stream<Double> generateStream = Stream.generate(Math::random).limit(5);
        System.out.println("Stream.generate: " + generateStream.collect(Collectors.toList()));

        // Stream.iterate
        Stream<Integer> iterateStream = Stream.iterate(0, n -> n + 2).limit(5);
        System.out.println("Stream.iterate: " + iterateStream.collect(Collectors.toList()));

        // BufferedReader.lines
        BufferedReader reader = new BufferedReader(new StringReader("Line1\nLine2\nLine3"));
        Stream<String> linesStream = reader.lines();
        System.out.println("BufferedReader.lines: " + linesStream.collect(Collectors.toList()));

        // IntStream.range
        IntStream rangeStream = IntStream.range(1, 5);
        System.out.println("IntStream.range: " + rangeStream.boxed().collect(Collectors.toList()));

        // Stream.empty
        Stream<Object> emptyStream = Stream.empty();
        System.out.println("Stream.empty: " + emptyStream.collect(Collectors.toList()));

        // 2. Intermediate Operations
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // filter
        System.out.println("filter: " + numbers.filter(n -> n % 2 == 0).collect(Collectors.toList()));

        // map
        numbers = Stream.of(1, 2, 3, 4, 5);
        System.out.println("map: " + numbers.map(n -> n * 2).collect(Collectors.toList()));

        // flatMap
        Stream<List<Integer>> nestedListStream = Stream.of(List.of(1, 2), List.of(3, 4), List.of(5));
        System.out.println("flatMap: " + nestedListStream.flatMap(Collection::stream).collect(Collectors.toList()));

        // distinct
        numbers = Stream.of(1, 2, 2, 3, 3, 3, 4);
        System.out.println("distinct: " + numbers.distinct().collect(Collectors.toList()));

        // sorted
        numbers = Stream.of(5, 3, 1, 4, 2);
        System.out.println("sorted: " + numbers.sorted().collect(Collectors.toList()));

        // limit
        numbers = Stream.iterate(1, n -> n + 1);
        System.out.println("limit: " + numbers.limit(3).collect(Collectors.toList()));

        // skip
        numbers = Stream.iterate(1, n -> n + 1);
        System.out.println("skip: " + numbers.skip(2).limit(3).collect(Collectors.toList()));

        // peek
        numbers = Stream.of(1, 2, 3);
        System.out.println("peek: " + numbers.peek(System.out::println).collect(Collectors.toList()));

        // mapToInt
        Stream<String> stringStream = Stream.of("a", "ab", "abc");
        System.out.println("mapToInt: " + stringStream.mapToInt(String::length).boxed().collect(Collectors.toList()));

        // takeWhile
        numbers = Stream.iterate(1, n -> n + 1);
        System.out.println("takeWhile: " + numbers.takeWhile(n -> n < 5).collect(Collectors.toList()));

        // dropWhile
        numbers = Stream.iterate(1, n -> n + 1).limit(10);
        System.out.println("dropWhile: " + numbers.dropWhile(n -> n < 5).collect(Collectors.toList()));

        // 3. Terminal Operations
        numbers = Stream.of(1, 2, 3, 4, 5);

        // forEach
        numbers.forEach(System.out::println);

        numbers = Stream.of(1, 2, 3, 4, 5);

        // collect
        System.out.println("collect: " + numbers.collect(Collectors.toList()));

        // reduce
        numbers = Stream.of(1, 2, 3, 4, 5);
        System.out.println("reduce: " + numbers.reduce(0, Integer::sum));

        // findFirst
        numbers = Stream.of(1, 2, 3);
        System.out.println("findFirst: " + numbers.findFirst().orElse(null));

        // findAny
        numbers = Stream.of(1, 2, 3);
        System.out.println("findAny: " + numbers.findAny().orElse(null));

        // count
        numbers = Stream.of(1, 2, 3, 4, 5);
        System.out.println("count: " + numbers.count());

        // min
        numbers = Stream.of(5, 3, 1, 4, 2);
        System.out.println("min: " + numbers.min(Comparator.naturalOrder()).orElse(null));

        // max
        numbers = Stream.of(5, 3, 1, 4, 2);
        System.out.println("max: " + numbers.max(Comparator.naturalOrder()).orElse(null));

        // anyMatch
        numbers = Stream.of(1, 2, 3);
        System.out.println("anyMatch: " + numbers.anyMatch(n -> n > 2));

        // allMatch
        numbers = Stream.of(1, 2, 3);
        System.out.println("allMatch: " + numbers.allMatch(n -> n > 0));

        // noneMatch
        numbers = Stream.of(1, 2, 3);
        System.out.println("noneMatch: " + numbers.noneMatch(n -> n < 0));

        // toArray
        numbers = Stream.of(1, 2, 3);
        Integer[] array = numbers.toArray(Integer[]::new);
        System.out.println("toArray: " + Arrays.toString(array));
    }
}
