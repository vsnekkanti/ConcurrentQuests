import java.util.Arrays;

public class ArrayTricks {

    public static void main(String[] args) {

        // 1. System.arraycopy example
        int[] sourceArray = {1, 2, 3, 4, 5};
        int[] destinationArray = new int[5];
        System.arraycopy(sourceArray, 1, destinationArray, 1, 3);
        System.out.println("System.arraycopy output: " + Arrays.toString(destinationArray));
        // Output: [1, 2, 3, 4, 5]

        // 2. Arrays.copyOf example (String[])
        String[] originalFruits = {"Apple", "Banana", "Cherry"};
        String[] copyExtended = Arrays.copyOf(originalFruits, 5);
        System.out.println("Arrays.copyOf (String[] to larger) output: " + Arrays.toString(copyExtended));
        // Output: [Apple, Banana, Cherry, null, null]

        // 3. Arrays.copyOf example (int[])
        int[] originalNumbers = {1, 2, 3};
        int[] copiedNumbers = Arrays.copyOf(originalNumbers, 5);
        System.out.println("Arrays.copyOf (int[] to larger) output: " + Arrays.toString(copiedNumbers));
        // Output: [1, 2, 3, 0, 0]

        // 4. Arrays.copyOf with newType (Integer[] to Number[])
        Integer[] originalIntegers = {1, 2, 3};
        Number[] copiedNumbersWithType = Arrays.copyOf(originalIntegers, 5, Number[].class);
        System.out.println("Arrays.copyOf (Integer[] to Number[]) output: " + Arrays.toString(copiedNumbersWithType));
        // Output: [1, 2, 3, null, null]

        // 5. Arrays.copyOfRange example
        int[] rangeCopy = Arrays.copyOfRange(sourceArray, 1, 4);
        System.out.println("Arrays.copyOfRange output: " + Arrays.toString(rangeCopy));
        // Output: [2, 3, 4]
    }
}
