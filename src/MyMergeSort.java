import java.util.Arrays;

public class MyMergeSort {

    public static void main(String[] args) {

        int[] array = {38, 27};
        System.out.println("Original Array: " + Arrays.toString(array));

        mergeSort(array);

        // Perform merge sort
        System.out.println("Sorted Array: " + Arrays.toString(array));

    }

    private static void mergeSort(int[] array) {
        int length = array.length;
        if(array == null || length < 2) {
            return;
        }
        int middleIndex = length / 2;
        int[] leftHalf = Arrays.copyOfRange(array, 0, middleIndex);
        int[] rightHalf = Arrays.copyOfRange(array, middleIndex, length);

        mergeSort(leftHalf);
        mergeSort(rightHalf);

        merge(array, leftHalf, rightHalf);

    }

    private static void merge(int[] array, int[] leftHalf, int[] rightHalf) {
        int leftIndex = 0, rightIndex = 0, mergeIndex = 0;


        while(leftIndex < leftHalf.length && rightIndex <rightHalf.length) {
            if (leftHalf[leftIndex] < rightHalf[rightIndex]) {
                array[mergeIndex] = leftHalf[leftIndex];
                leftIndex++;
                mergeIndex++;
            } else {
                array[mergeIndex] = rightHalf[rightIndex];
                rightIndex++;
                mergeIndex++;
            }
        }

        if(leftIndex < leftHalf.length) {
            System.arraycopy(leftHalf, leftIndex, array, mergeIndex, leftHalf.length - leftIndex);
        }
        if(rightIndex < rightHalf.length) {
            System.arraycopy(rightHalf, rightIndex, array, mergeIndex, rightHalf.length - rightIndex);
        }


        }




    }


