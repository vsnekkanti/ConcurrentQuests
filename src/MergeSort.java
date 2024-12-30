import java.util.Arrays;

public class MergeSort {
    // Main function to perform Merge Sort
    public static void mergeSort(int[] array) {
        if (array == null || array.length < 2) {
            return; // Base case: array is already sorted if it has 0 or 1 element
        }
        int middleIndex = array.length / 2;
        int[] leftHalf = Arrays.copyOfRange(array, 0, middleIndex); // Left half of the array
        int[] rightHalf = Arrays.copyOfRange(array, middleIndex, array.length); // Right half of the array

        mergeSort(leftHalf); // Recursively sort the left half
        mergeSort(rightHalf); // Recursively sort the right half

        merge(array, leftHalf, rightHalf); // Merge the two sorted halves
    }

    // Function to merge two sorted arrays into one sorted array
    public static void merge(int[] array, int[] leftHalf, int[] rightHalf) {
        int leftIndex = 0, rightIndex = 0, mergeIndex = 0;

        // Compare elements from leftHalf and rightHalf, and merge them into the original array
        while (leftIndex < leftHalf.length && rightIndex < rightHalf.length) {
            if (leftHalf[leftIndex] <= rightHalf[rightIndex]) {
                array[mergeIndex] = leftHalf[leftIndex];
                leftIndex++;
            } else {
                array[mergeIndex] = rightHalf[rightIndex];
                rightIndex++;
            }
            mergeIndex++;
        }

        // Copy remaining elements from leftHalf, if any
        if (leftIndex < leftHalf.length) {
            System.arraycopy(leftHalf, leftIndex, array, mergeIndex, leftHalf.length - leftIndex);
        }

        // Copy remaining elements from rightHalf, if any
        if (rightIndex < rightHalf.length) {
            System.arraycopy(rightHalf, rightIndex, array, mergeIndex, rightHalf.length - rightIndex);
        }
    }

    // Entry point to test the Merge Sort
    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Original Array: " + Arrays.toString(array));
        mergeSort(array); // Perform merge sort
        System.out.println("Sorted Array: " + Arrays.toString(array));
    }
}
