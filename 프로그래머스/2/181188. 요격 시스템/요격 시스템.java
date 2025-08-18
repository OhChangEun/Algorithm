import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> a[1] - b[1]); // 
       
        int count = 0;
        double currMissle = -1;
        for (int[] target: targets) {
            int start = target[0];
            int end = target[1];
          
            if (currMissle < start) {
                currMissle = end - 0.5;
                count++;
            }
        }
        
        return count;
    }
}