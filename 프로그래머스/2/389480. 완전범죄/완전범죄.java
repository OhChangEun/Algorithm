import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        
        int len = info.length; 
       
        // b 비용이 들 때 최소 a 비용 
        int[] dp = new int[m]; 
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 0; i < len; i++) {
            int aCost = info[i][0];
            int bCost = info[i][1];
            
            int[] next = new int[m];
            Arrays.fill(next, Integer.MAX_VALUE);
          
            for (int b = 0; b < m; b++) {
                if (dp[b] == Integer.MAX_VALUE) continue;
                
                int newA = dp[b] + aCost; // 현재 b흔적에 알고 있는 최소 a 흔적에 a 흔적을 추가해서
                if (newA < n) {
                    next[b] = Math.min(next[b], newA); // a 흔적 갱신
                }
                
                int newB = b + bCost; // 현재 b에 b흔적을 추가해서 만들어지는 b 흔적에 맞는 a최소 비용은 
                if (newB < m) {
                    next[newB] = Math.min(next[newB], dp[b]); // 현재까지 알고 있는 최소 a 흔적과 비교
                }
            }
            
            dp = next;
        }
        
        int minA = Integer.MAX_VALUE;
        for (int a: dp) {
            minA = Math.min(minA, a);
        }

        /*
        for (int num: dp) {
            System.out.print(num + " ");
        }
        */
        
        return minA == Integer.MAX_VALUE ? -1 : minA;
    }
}