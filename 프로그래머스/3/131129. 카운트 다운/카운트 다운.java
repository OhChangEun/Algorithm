class Solution {
    public int[] solution(int target) {
        int[][] dp = new int[target + 1][2];
        for (int i=1; i<=target; i++) {
            dp[i][0] = Integer.MAX_VALUE; 
        }
        
        for (int score = 1; score <= target; score++) {
            for (int single = 1; single <= 20; single++) {
                int prev = score - single;
                if (prev >= 0 && dp[prev][0] != Integer.MAX_VALUE) {
                    update(dp, score, dp[prev][0] + 1, dp[prev][1] + 1);
                }
            } 
            for (int num = 1; num <= 20; num++) {
                int doubleScore = num * 2;
                int prev = score - doubleScore;
                if (prev >= 0 && dp[prev][0] != Integer.MAX_VALUE) {
                    update(dp, score, dp[prev][0] + 1, dp[prev][1]);
                }
            } 
            for (int num = 1; num <= 20; num++) {
                int tripleScore = num * 3;
                int prev = score - tripleScore;
                if (prev >= 0 && dp[prev][0] != Integer.MAX_VALUE) {
                    update(dp, score, dp[prev][0] + 1, dp[prev][1]);
                }
            } 
            int prev = score - 50;
            if (prev >= 0 && dp[prev][0] != Integer.MAX_VALUE) {
                update(dp, score, dp[prev][0] + 1, dp[prev][1] + 1);
            }
        }
        
        return dp[target];
    }
    
    private void update(int[][] dp, int score, int total, int singleAndBull) {
        if (total < dp[score][0]) {
            dp[score][0] = total;
            dp[score][1] = singleAndBull;
        } else if (total == dp[score][0] && singleAndBull > dp[score][1]) {
            dp[score][1] = singleAndBull; 
        }
    }
}