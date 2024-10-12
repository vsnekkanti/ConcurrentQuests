import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MedianOfTwoSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array to optimize binary search
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int m = nums1.length;
        int n = nums2.length;
        
        int low = 0, high = m;
        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (m + n + 1) / 2 - partitionX;
            
            // Edge cases: If partitionX == 0, it means nothing is on the left side of nums1
            // If partitionX == m, it means everything is on the left side of nums1
            int maxX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];
            
            int maxY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];
            
            if (maxX <= minY && maxY <= minX) {
                // We have partitioned correctly, now find the median
                if ((m + n) % 2 == 0) {
                    return ((double) Math.max(maxX, maxY) + Math.min(minX, minY)) / 2;
                } else {
                    return (double) Math.max(maxX, maxY);
                }
            } else if (maxX > minY) {
                // Move towards the left side of nums1
                high = partitionX - 1;
            } else {
                // Move towards the right side of nums1
                low = partitionX + 1;
            }
        }
        
        throw new IllegalArgumentException("Input arrays are not sorted");
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 8, 9 ,14};
        int[] nums2 = {2, 4};


int[] nums3 = IntStream.concat(Arrays.stream(nums1), Arrays.stream(nums2)).sorted().toArray();
        System.out.println(Arrays.toString(nums3));


        System.out.println(findMedianSortedArrays(nums1, nums2)); // Output: 2.0
    }
}
