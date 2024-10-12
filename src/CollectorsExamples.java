import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExamples {

    public static void main(String[] args) {
        toMapExamples();
        groupingByExamples();
        partitioningByExamples();
        mappingExamples();
        reducingExamples();
        otherCollectorsExamples();
    }

    // Variants of Collectors.toMap
    private static void toMapExamples() {
        // Example 1: toMap(Function keyMapper, Function valueMapper)
        List<String> list1 = Arrays.asList("a", "bb", "ccc", "dd");
        Map<String, Integer> map1 = list1.stream()
            .collect(Collectors.toMap(
                    s -> s, //keymapper
                    s -> s.length() //valuemapper
                    )
            );
        System.out.println("toMap Example 1: " + map1); // Output: {a=1, bb=2, ccc=3, dd=2}

        // Example 2: toMap(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction)
        List<String> list2 = Arrays.asList("a", "bb", "ccc", "dd", "cc");
        Map<Integer, String> map2 = list2.stream()
            .collect(Collectors.toMap(
                s -> s.length(), //keyMapper
                s -> s, //valueMapper
                (s1, s2) -> s1 + "," + s2) //mergeFunction
            );
        System.out.println("toMap Example 2: " + map2); // Output: {1=a, 2=bb,dd, 3=ccc,cc}

        // Example 3: toMap(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction, Supplier mapSupplier)
        Map<Integer, String> map3 = list2.stream()
            .collect(Collectors.toMap(
                s -> s.length(), ////keyMapper
                s -> s, //valueMapper
                (s1, s2) -> s1 + "," + s2, //mergeFunction
                TreeMap::new //mapSupplier
                    ));
        System.out.println("toMap Example 3: " + map3); // Output: {1=a, 2=bb,dd, 3=ccc,cc}
    }

    // Variants of Collectors.groupingBy
    private static void groupingByExamples() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Edward");

        // Example 1: Simple Grouping By a Classifier Function
        Map<Character, List<String>> groupedByFirstLetter = names.stream()
            .collect(Collectors.groupingBy(name -> name.charAt(0)));
        System.out.println("groupingBy Example 1: " + groupedByFirstLetter); // Output: {A=[Alice], B=[Bob], C=[Charlie], D=[David], E=[Edward]}

        // Example 2: Grouping By a Classifier Function with a Downstream Collector
        Map<Character, Long> countByFirstLetter = names.stream()
            .collect(Collectors.groupingBy(name -> name.charAt(0), Collectors.counting()));
        System.out.println("groupingBy Example 2: " + countByFirstLetter); // Output: {A=1, B=1, C=1, D=1, E=1}

        // Example 3: Grouping By a Classifier Function with a Specific Map Supplier and Downstream Collector
        TreeMap<Character, Long> countByFirstLetterTreeMap = names.stream()
            .collect(Collectors.groupingBy(name -> name.charAt(0), TreeMap::new, Collectors.counting()));
        System.out.println("groupingBy Example 3: " + countByFirstLetterTreeMap); // Output: {A=1, B=1, C=1, D=1, E=1}
    }

    // Variants of Collectors.partitioningBy
    private static void partitioningByExamples() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Variant 1: Collectors.partitioningBy(Predicate<? super T> predicate)
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
            .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("partitioningBy Example 1: Even numbers: " + partitioned.get(true) + ", Odd numbers: " + partitioned.get(false));
        // Output: Even numbers: [2, 4, 6, 8, 10], Odd numbers: [1, 3, 5, 7, 9]

        // Variant 2: Collectors.partitioningBy(Predicate<? super T> predicate, Collector<? super T, A, D> downstream)
        Map<Boolean, Long> partitionedCount = numbers.stream()
            .collect(Collectors.partitioningBy(n -> n % 2 == 0, Collectors.counting()));
        System.out.println("partitioningBy Example 2: Count of even numbers: " + partitionedCount.get(true) + ", Count of odd numbers: " + partitionedCount.get(false));
        // Output: Count of even numbers: 5, Count of odd numbers: 5
    }

    // Collectors.mapping Examples
    private static void mappingExamples() {
        List<Person> people = Arrays.asList(
            new Person("John", 30),
            new Person("Jane", 25),
            new Person("Tom", 30),
            new Person("Lucy", 25)
        );

        // Example 1: Using Collectors.mapping with Collectors.groupingBy
        Map<Integer, List<String>> peopleByAge = people.stream()
            .collect(Collectors.groupingBy(
                Person::getAge, 
                Collectors.mapping(Person::getName, Collectors.toList())
            ));
        System.out.println("mapping Example 1: " + peopleByAge); // Output: {25=[Jane, Lucy], 30=[John, Tom]}

        // Example 2: Using Collectors.mapping with Collectors.partitioningBy
        Map<Boolean, List<String>> partitionedByAge = people.stream()
            .collect(Collectors.partitioningBy(
                person -> person.getAge() > 28, 
                Collectors.mapping(Person::getName, Collectors.toList())
            ));
        System.out.println("mapping Example 2: " + partitionedByAge); // Output: {false=[Jane, Lucy], true=[John, Tom]}
    }

    // Variants of Collectors.reducing
    private static void reducingExamples() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Example 1: Single Argument Collectors.reducing(BinaryOperator<T> op)
        Optional<Integer> sum = numbers.stream().collect(Collectors.reducing(Integer::sum));
        sum.ifPresent(s -> System.out.println("reducing Example 1: " + s)); // Output: 15

        // Example 2: Two Arguments Collectors.reducing(T identity, BinaryOperator<T> op)
        Integer sumWithIdentity = numbers.stream().collect(Collectors.reducing(0, Integer::sum));
        System.out.println("reducing Example 2: " + sumWithIdentity); // Output: 15

        // Example 3: Three Arguments Collectors.reducing(U identity, Function<? super T,? extends U> mapper, BinaryOperator<U> op)
        List<String> strings = Arrays.asList("a", "bb", "ccc", "dddd");
        Integer totalLength = strings.stream()
                             .collect(Collectors.reducing(0, String::length, Integer::sum));
        System.out.println("reducing Example 3: " + totalLength); // Output: 10
    }

    // Other Collectors methods examples
    private static void otherCollectorsExamples() {
        // Example 1: Collectors.maxBy
        Stream<Integer> numbersStream1 = Stream.of(3, 5, 10, 1, 7);
        Optional<Integer> maxNumber = numbersStream1.collect(Collectors.maxBy(Comparator.naturalOrder()));
        maxNumber.ifPresent(n -> System.out.println("maxBy Example: " + n));  // Output: 10

        // Example 2: Collectors.minBy
        Stream<Integer> numbersStream2 = Stream.of(3, 5, 10, 1, 7);
        Optional<Integer> minNumber = numbersStream2.collect(Collectors.minBy(Comparator.naturalOrder()));
        minNumber.ifPresent(n -> System.out.println("minBy Example: " + n));  // Output: 1

        // Example 3: Collectors.reducing
        Stream<Integer> numbersStream3 = Stream.of(3, 5, 10, 1, 7);
        Optional<Integer> sum = numbersStream3.collect(Collectors.reducing((a, b) -> a + b));
        sum.ifPresent(s -> System.out.println("reducing Example: " + s));  // Output: 26
        

        // Example 4: Collectors.collectingAndThen
        Stream<String> wordsStream = Stream.of("a", "b", "c", "d");
        List<String> wordList = wordsStream.collect(Collectors.collectingAndThen(
                Collectors.toList(),
                Collections::unmodifiableList
        ));
        System.out.println("collectingAndThen Example: " + wordList);  // Output: [a, b, c, d]

        // Example 5: Collectors.summarizingInt
        List<Integer> numbers = Arrays.asList(3, 5, 10, 1, 7);
        IntSummaryStatistics stats = numbers.stream().collect(Collectors.summarizingInt(Integer::intValue));

        System.out.println("summarizingInt Example: " + stats);
        // Output: IntSummaryStatistics{count=5, sum=26, min=1, average=5.200000, max=10}
    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
