import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


class Person {
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
  
public class SortingDemo {
  
    public static void main(String[] f){  
        // Primitive int array  
        int[] intArray = {5, 3, 9, 1, 4};  
        Arrays.sort(intArray);  
        System.out.println("Sorted int Array: " + Arrays.toString(intArray));  
  
        // Primitive char array  
        char[] charArray = {'e', 'c', 'i', 'a', 'd'};  
        Arrays.sort(charArray);  
        System.out.println("Sorted char Array: " + Arrays.toString(charArray));  
  
        // Object Character array  
        Character[] characterArray = {'e', 'c', 'i', 'a', 'd'};  
        Arrays.sort(characterArray);  
        System.out.println("Sorted Character Array: " + Arrays.toString(characterArray));  
  
        // String array  
        String[] stringArray = {"apple", "orange", "banana", "grape", "pear"};  
        Arrays.sort(stringArray);  
        System.out.println("Sorted String Array: " + Arrays.toString(stringArray));  
  
        // Array  
        Integer[] array = {5, 3, 9, 1, 4};  
        Arrays.sort(array);  
        System.out.println("Sorted Array: " + Arrays.toString(array));  
  
        // ArrayList  
        List<Integer> arrayList = new ArrayList<>(Arrays.asList(5, 3, 9, 1, 4));  
        Collections.sort(arrayList);  
        System.out.println("Sorted ArrayList: " + arrayList);  
  
        // LinkedList  
        List<Integer> linkedList = new LinkedList<>(Arrays.asList(5, 3, 9, 1, 4));
        Collections.sort(linkedList);  
        System.out.println("Sorted LinkedList: " + linkedList);  
  
        // HashSet (convert to list to sort)  
        Set<Integer> hashSet = new HashSet<>(Arrays.asList(5, 3, 9, 1, 4));  
        List<Integer> sortedHashSet = new ArrayList<>(hashSet);  
        Collections.sort(sortedHashSet);  
        System.out.println("Sorted HashSet: " + sortedHashSet);  
  
        // TreeSet (automatically sorted)  
        Set<Integer> treeSet = new TreeSet<>(Arrays.asList(5, 3, 9, 1, 4));  
        System.out.println("TreeSet (sorted): " + treeSet);  
  
        // PriorityQueue (convert to list to sort)  
        Queue<Integer> priorityQueue = new PriorityQueue<>(Arrays.asList(5, 3, 9, 1, 4));  
        List<Integer> sortedPriorityQueue = new ArrayList<>(priorityQueue);  
        Collections.sort(sortedPriorityQueue);  
        System.out.println("Sorted PriorityQueue: " + sortedPriorityQueue);  
  
        // HashMap (sort by keys)  
        Map<Integer, String> hashMap = new HashMap<>();  
        hashMap.put(5, "five");  
        hashMap.put(3, "three");  
        hashMap.put(9, "nine");  
        hashMap.put(1, "one");  
        hashMap.put(4, "four");  
        Map<Integer, String> sortedHashMap = new LinkedHashMap<>();

        // Map Sort by Keys Ascending Order
        Map<Integer, String> mysortedlinkedhashmap = hashMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // keymapper
                        Map.Entry::getValue, // valuemapper
                        (oldValue, newValue) -> oldValue, // mergefunction
                        LinkedHashMap::new // map supplier
                ));
        System.out.println("Sorted by Keys (Ascending): " + mysortedlinkedhashmap);

// Map Sort by Keys Ascending Order (Alternative)
        mysortedlinkedhashmap = hashMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(
                        LinkedHashMap::new, // supplier
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()), // accumulator
                        LinkedHashMap::putAll // combiner
                );
        System.out.println("Sorted by Keys (Ascending) Alternative: " + mysortedlinkedhashmap);

// Map Sort by Keys Descending Order
        mysortedlinkedhashmap = hashMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, String>comparingByKey().reversed())
                .collect(
                        LinkedHashMap::new, // supplier
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()), // accumulator
                        LinkedHashMap::putAll // combiner
                );
        System.out.println("Sorted by Keys (Descending): " + mysortedlinkedhashmap);

// Map Sort by Values Ascending Order
        mysortedlinkedhashmap = hashMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // keymapper
                        Map.Entry::getValue, // valuemapper
                        (oldValue, newValue) -> oldValue, // mergefunction
                        LinkedHashMap::new // map supplier
                ));
        System.out.println("Sorted by Values (Ascending): " + mysortedlinkedhashmap);

// Map Sort by Values Ascending Order (Alternative)
        mysortedlinkedhashmap = hashMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(
                        LinkedHashMap::new, // supplier
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()), // accumulator
                        LinkedHashMap::putAll // combiner
                );
        System.out.println("Sorted by Values (Ascending) Alternative: " + mysortedlinkedhashmap);

// Map Sort by Values Descending Order
        mysortedlinkedhashmap = hashMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, String>comparingByValue().reversed())
                .collect(
                        LinkedHashMap::new, // supplier
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()), // accumulator
                        LinkedHashMap::putAll // combiner
                );
        System.out.println("Sorted by Values (Descending): " + mysortedlinkedhashmap);
  
        // LinkedHashMap (sort by keys)  
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();  
        linkedHashMap.put(5, "five");  
        linkedHashMap.put(3, "three");  
        linkedHashMap.put(9, "nine");  
        linkedHashMap.put(1, "one");  
        linkedHashMap.put(4, "four");  
        Map<Integer, String> sortedLinkedHashMap = new TreeMap<>(linkedHashMap);  
  
//sort the values  
        System.out.println("Sorted LinkedHashMap by keys: " + sortedLinkedHashMap);  
  
        // TreeMap (automatically sorted by keys)  
        Map<Integer, String> treeMap = new TreeMap<>();  
        treeMap.put(5, "five");  
        treeMap.put(3, "three");  
        treeMap.put(9, "nine");  
        treeMap.put(1, "one");  
        treeMap.put(4, "four");  
        System.out.println("TreeMap (sorted by keys): " + treeMap);  
  
        // ConcurrentHashMap (sort by keys)  
        Map<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();  
        concurrentHashMap.put(5, "five");  
        concurrentHashMap.put(3, "three");  
        concurrentHashMap.put(9, "nine");  
        concurrentHashMap.put(1, "one");  
        concurrentHashMap.put(4, "four");  
        Map<Integer, String> sortedConcurrentHashMap = new TreeMap<>(concurrentHashMap);  
        System.out.println("Sorted ConcurrentHashMap by keys: " + sortedConcurrentHashMap);  
  
        // ArrayDeque (convert to list to sort)  
        Deque<Integer> arrayDeque = new ArrayDeque<>(Arrays.asList(5, 3, 9, 1, 4));  
        List<Integer> sortedArrayDeque = new ArrayList<>(arrayDeque);  
        Collections.sort(sortedArrayDeque);  
        System.out.println("Sorted ArrayDeque: " + sortedArrayDeque);  
  
        // BlockingQueue (convert to list to sort)  
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(5);  
        blockingQueue.add(5);  
        blockingQueue.add(3);  
        blockingQueue.add(9);  
        blockingQueue.add(1);  
        blockingQueue.add(4);  
        List<Integer> sortedBlockingQueue = new ArrayList<>(blockingQueue);  
        Collections.sort(sortedBlockingQueue);  
        System.out.println("Sorted BlockingQueue: " + sortedBlockingQueue);  
  
        // Vector  
        Vector<Integer> vector = new Vector<>(Arrays.asList(5, 3, 9, 1, 4));  
        Collections.sort(vector);  
        System.out.println("Sorted Vector: " + vector);  
  
  
        // Stack  
        Stack<Integer> stack = new Stack<>();  
        stack.push(5);  
        stack.push(3);  
        stack.push(9);  
        stack.push(1);  
        stack.push(4);  
        System.out.println("Stack before sorting: " + stack);  
        List<Integer> stackList = new ArrayList<>(stack);  
        Collections.sort(stackList);  
        stack.clear();  
        stackList.forEach(stack::push);  
        System.out.println("Stack after sorting: " + stack);


        // Different variant examples of Comparator.comparing
        List<Person> people = Arrays.asList(new Person("John", 30), new Person("Alice", 25), new Person("Bob", 35));
        Comparator<Person> comparator = Comparator.comparing(Person::getName);
        people.sort(comparator);
        System.out.println("Sorted People by Name: " + people);

        comparator = Comparator.comparing(Person::getName).reversed();
        people.sort(comparator);
        System.out.println("Sorted People by Name in Reverse Order: " + people);

        comparator = Comparator.comparing(Person::getAge).thenComparing(Person::getName);
        people.sort(comparator);
        System.out.println("Sorted People by Age and then by Name: " + people);

        comparator = Comparator.comparing(Person::getName, String.CASE_INSENSITIVE_ORDER);
        people.sort(comparator);
        System.out.println("Sorted People by Name (Case-Insensitive): " + people);

        comparator = Comparator.comparingInt(Person::getAge);
        people.sort(comparator);
        System.out.println("Sorted People by Age: " + people);

        comparator = Comparator.comparing(Person::getName, Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER));
        people.sort(comparator);
        System.out.println("Sorted People by Name (Nulls First, Case-Insensitive): " + people);
  
    }  
}