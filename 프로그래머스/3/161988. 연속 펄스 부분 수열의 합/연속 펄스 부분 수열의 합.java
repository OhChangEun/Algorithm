import java.util.*;

class Solution {
    public long kadane(int[] arr) {
        long currSum = 0;
        long maxSum = arr[0];
        
        for (int num: arr) {
            currSum = Math.max(num, num + currSum); 
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
        
    public long solution(int[] sequence) {
   		int size = sequence.length;
        int[] arr1 = Arrays.copyOf(sequence, size);
        int[] arr2 = Arrays.copyOf(sequence, size);
  
        for (int i=0; i<size; i++) {
            if (i % 2 == 0) 
           		arr1[i] *= -1;
            else 
                arr2[i] *= -1;
        }
        
        long res1 = kadane(arr1);
        long res2 = kadane(arr2);
        
        return Math.max(res1, res2);
    }
}