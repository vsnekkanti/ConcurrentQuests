import java.util.*;



public class Java21FeaturesDemo {

    public static void main(String[] args) {
        // 1. Pattern Matching for Switch
        Object obj = "Hello, Java 21!";
        System.out.println("Pattern Matching for switch:");
        printObjectType(obj);

        // 2. Sequenced Collections
        System.out.println("\nSequenced Collections:");
        List<String> sequencedList = new ArrayList<>(List.of("A", "B", "C"));
        System.out.println("Original list: " + sequencedList);
        System.out.println("First element: " + sequencedList.getFirst());
        System.out.println("Last element: " + sequencedList.getLast());

        sequencedCollectionsDemo();

        // 3. Unnamed Patterns and Variables in Switch
        System.out.println("\nUnnamed Patterns in Switch:");
        printWithUnnamedPatterns(100);

        // 4. Record Patterns
        System.out.println("\nRecord Patterns:");
        Person person = new Person("Alice", 30);
        printPersonDetails(person);

        // 5. String Templates (Preview Feature)
        System.out.println("\nString Templates:");
        int age = 30;
        String name = "Alice";
        String formattedString = STR."My name is \{name} and I am \{age} years old.";
        System.out.println(formattedString);
    }

    // 1. Pattern Matching for Switch (Java 21)
    private static void printObjectType(Object obj) {
        switch (obj) {
            case Integer i -> System.out.println("Integer: " + i);
            case String s -> System.out.println("String: " + s);
            case null -> { System.out.println("Null value");
                System.out.println("ouness null");
            }
            default -> System.out.println("Unknown type");
        }
    }

    // 2. Sequenced Collections (Java 21)
    private static void sequencedCollectionsDemo() {
        SequencedCollection<String> sequencedCollection = new ArrayList<>(List.of("first", "second", "third"));
        SequencedSet<String> ss = new TreeSet<>(List.of("first", "second", "third"));
//        ss.reversed();
        System.out.println(ss.reversed());
        System.out.println(ss);
//        System.out.println("First element: " + sequencedCollection.getFirst());
//        System.out.println("Last element: " + sequencedCollection.getLast());
    }

    // 3. Unnamed Patterns and Variables in Switch (Java 21)
    private static void printWithUnnamedPatterns(Object obj) {
        switch (obj) {
            case String _ -> System.out.println("It's a String, but we don't need its value");
            case Integer i -> System.out.println("Integer: " + i);
            case null, default -> System.out.println("Null or unknown type");
        }
    }

    // 4. Record Patterns (Java 21)
    record Person(String name, int age) {}

    private static void printPersonDetails(Object obj) {
        switch (obj) {
            case Person(String name, int age) -> System.out.println("Name: " + name + ", Age: " + age);
            default -> System.out.println("Not a person");
        }
    }

    // 5. String Templates (Preview Feature in Java 21)
    private static String formatPersonDetails(String name, int age) {
        return STR."Hello, my name is \{name} and I am \{age} years old.";
    }
}
