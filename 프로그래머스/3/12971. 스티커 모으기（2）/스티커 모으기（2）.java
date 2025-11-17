import java.util.*;

class Solution {
    public int getMax(int[] sticker) {
        int n = sticker.length;
        if (n == 1) {
            return sticker[0];
        }
   		int[] dp = new int[n];
        
        dp[0] = sticker[0];
        dp[1] = Math.max(sticker[0], sticker[1]);
    
        for (int i=2; i<n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + sticker[i]);
        }
        return dp[n - 1];
    }
    
    public int solution(int sticker[]) {
        int n = sticker.length;
        if (n == 1) {
            return sticker[0];
        }
        
        int max1 = getMax(Arrays.copyOfRange(sticker, 1, n));
        int max2 = getMax(Arrays.copyOfRange(sticker, 0, n-1));
        
        return Math.max(max1, max2);
    }
}