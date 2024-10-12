import java.util.HashMap;
import java.util.Map;

public class StringCompression {

    public static String compressString(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder compressed = new StringBuilder();
        Map<Character, Integer> charCountMap = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            // Update the count for the current character in the map
            charCountMap.put(currentChar, charCountMap.getOrDefault(currentChar, 0) + 1);

            // If the next character is different or we are at the last character
            if (i == input.length() - 1 || input.charAt(i + 1) != currentChar) {
                // Append the character and its count to the result
                compressed.append(currentChar);
                int count = charCountMap.get(currentChar);
                if (count > 1) {
                    compressed.append(count);
                }
                // Reset the map for the next character sequence
                charCountMap.clear();
            }
        }

        return compressed.toString();
    }

    public static void main(String[] args) {
        String input = "AAABBCDDAA";
        String output = compressString(input);
        System.out.println("Compressed String: " + output);
    }
}
