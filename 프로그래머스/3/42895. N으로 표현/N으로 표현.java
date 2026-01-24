import java.util.*;

class Solution {
    public int solution(int N, int target) {
        Set<Integer>[] dp = new HashSet[9];
        for (int i = 1; i < 9; i++) {
            dp[i] = new HashSet<>();
        }
        
        for (int i = 1; i < 9; i++) {
            int repeatedNum = Integer.parseInt(String.valueOf(N).repeat(i));
            dp[i].add(repeatedNum);
            
            for (int j = 1; j < i; j++) {
                for (int a: dp[j]) {
                    for (int b: dp[i - j]) {
                        dp[i].add(a + b);
                        dp[i].add(a - b);
                        dp[i].add(a * b);
                        if (b != 0) dp[i].add(a / b);
                        if (a != 0) dp[i].add(b / a);
                    }
                }
            }
            
            if (dp[i].contains(target)) 
                return i;
        }
       
        return -1;
    }
}