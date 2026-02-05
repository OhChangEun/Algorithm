import java.util.*;

class Solution {
    public int solution(int[] sticker) {
        int n = sticker.length; 
        
        if (n == 1) 
            return sticker[0];
        
        // 첫 스티커를 포함하는 경우 
        int max1 = getMaxValue(Arrays.copyOfRange(sticker, 0, n - 1));
      
        // 첫 스티커를 빼는 경우 
        int max2 = getMaxValue(Arrays.copyOfRange(sticker, 1, n));
        
        
       	return Math.max(max1, max2); 
    }
    
    private int getMaxValue(int[] stickers) {
        int n = stickers.length; 
        
        if (n == 1) 
            return stickers[0];
        
        int[] dp = new int[n]; 
        dp[0] = stickers[0];
        dp[1] = Math.max(stickers[0], stickers[1]);
        
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + stickers[i], dp[i- 1]);
        }
        
        return dp[n - 1];
    }
}