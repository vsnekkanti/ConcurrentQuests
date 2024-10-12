import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

//import static java.util.Map.entry;

public class StringCharFrequency {

    public static void main(String[] args) {

        String mystr = "aaabbbccccddeefffffff";


        Map<Character,Integer> map = new HashMap<>();


        Map<Character,Long> maplong = mystr.chars()
                .mapToObj(c-> (char) c)
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting())
                );

        System.out.println(maplong);


        Map<Character,Integer> mapint = mystr.chars()
                .mapToObj(c -> (char)c)
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.summingInt(c -> 1))
                );



        Map<Character,Integer> mapint2 = mystr.chars()
                .mapToObj(c -> (char)c)
                .collect(toMap(Function.identity(), c -> 1, Math::addExact));


        mystr.chars()
                .mapToObj(c -> (char)c)
                .forEach(e->map.put(e, map.getOrDefault(e, 0) + 1));


        mystr.chars()
                .mapToObj(c -> (char)c)
                .forEach(c->map.merge(c,1,Integer::sum)); //alternative


        Map<Character, Integer> frequencies = mystr.chars().boxed()
                                                   .collect(toMap(
                                                             // key = char
                                                             //k -> Character.valueOf((char) k.intValue()),
                                                             k -> (char) k.intValue(),
                                                             v -> 1,         // 1 occurence
                                                             Integer::sum)); // counting

        mystr.chars()
                .mapToObj(c -> (char)c)
                .forEach(c->map.merge(c,1,Integer::sum));

//Stream.of(mystr.toCharArray())


//        .forEach(ch -> {
//
//    System.out.println(ch);
//}
//);


//        Random random = new Random();
//        int min = 5;
//        int max = 15;
//        int randomNum = random.nextInt((max - min) + 1) + min;
//        System.out.println(randomNum);
//
//
//
//        System.out.println(map);

    }



}
