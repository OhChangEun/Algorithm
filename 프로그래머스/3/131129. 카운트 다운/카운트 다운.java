import java.util.*;

class Solution {
    public int[] solution(int target) {
        int[][] dp = new int[target + 1][2];

        for (int i = 1; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= target; i++) { 
            for (int s = 1; s <= 20; s++) {
                update(dp, i, i - s, true);
            }

            for (int s = 1; s <= 20; s++) {
                update(dp, i, i - s * 2, false);
            }
            
            for (int s = 1; s <= 20; s++) {
                update(dp, i, i - s * 3, false);
            }

            update(dp, i, i - 50, true);
        }

        return dp[target];
    }

    private void update(int[][] dp, int cur, int prev, boolean single) {
        if (prev < 0 || dp[prev][0] == Integer.MAX_VALUE) return;

        int dart = dp[prev][0] + 1;
        int singleCnt = dp[prev][1] + (single ? 1 : 0);

        if (dart < dp[cur][0]) {
            dp[cur][0] = dart;
            dp[cur][1] = singleCnt;
        } else if (dart == dp[cur][0]) {
            dp[cur][1] = Math.max(dp[cur][1], singleCnt);
        }
    }
}
