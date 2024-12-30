import java.util.*;
import java.util.stream.Collectors;

public class Practice {

    public static void main(String[] args) {
//
//        String hh = "smallwonder";
//        char[] smallchars = hh.toCharArray();
//
//      //  char array to Character array
//
//        Character[] bigchararray = new String(smallchars).chars().mapToObj(c -> (char) c).toArray(Character[]::new);
//
//
//      //  Character array to char array
//
//        char[] secsmal = Arrays.stream(bigchararray).map(String::valueOf).collect(Collectors.joining()).toCharArray();
//
        String anagram = "cat";
        String sortedStr = anagram.chars().mapToObj(c -> (char) c).sorted().map(String::valueOf).collect(Collectors.joining());
//
//        System.out.println(sortedStr);

//        TreeSet<String> sortedSet = new TreeSet<>(Arrays.asList(sortedStr.split(" ")));
        List<Integer> ll = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
ll.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        System.out.println(Collections.max(ll));

        System.out.println(Collections.min(ll)  );
//
        int[] tt = ll.stream().mapToInt(Integer::intValue).toArray();
       var ff = Arrays.stream(tt).boxed().collect(Collectors.toList());
        System.out.println(ff);

        System.out.println(Arrays.stream(tt).max().getAsInt());
//
//        Integer[] TT= Arrays.stream(tt).boxed().toArray(Integer[]::new);
//
//        System.out.println(Arrays.toString(tt));
    }
}
