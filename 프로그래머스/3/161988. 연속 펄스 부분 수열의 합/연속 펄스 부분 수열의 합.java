import java.util.*;

class Solution {
   
    public long kadane(int[] arr) {
        long currSum = arr[0];
        long maxSum = arr[0];
       
        for (int i=1; i<arr.length; i++) {
            currSum = Math.max(arr[i], currSum + arr[i]);
         	maxSum = Math.max(currSum, maxSum);   
        }
        return maxSum;
    }
    
    public long solution(int[] sequence) {
        long answer = 0;

        int size = sequence.length;
        int[] arr1 = Arrays.copyOf(sequence, size);
        int[] arr2 = Arrays.copyOf(sequence, size);
       
        for (int i=0; i<size; i++) {
            if (i%2 == 0) arr2[i] *= -1;
            else if (i%2 == 1) arr1[i] *= -1;
        }
        
        long max1 = kadane(arr1);
        long max2 = kadane(arr2);
        
        return Math.max(max1, max2);
    }
}