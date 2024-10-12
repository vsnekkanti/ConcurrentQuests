import java.util.*;
import java.util.stream.Collectors;

public class GroupAnagrams {

    public static void main(String[] args) {

        String[] anagras = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        Map<String, List<String>> categories = new HashMap<>();
                Arrays.stream(anagras).forEach(str->{
                    String sortedStr = str.chars().mapToObj(c->(char)c).sorted().map(String::valueOf).collect(Collectors.joining());
                    categories.computeIfPresent(sortedStr, (k,v)->{ v.add(str); return v; });
                    categories.computeIfAbsent(sortedStr, k -> new ArrayList<>(List.of(str)));

        });

        categories.forEach((k,v)->{
            System.out.println(k+ " "+v);
        });


    }
}
