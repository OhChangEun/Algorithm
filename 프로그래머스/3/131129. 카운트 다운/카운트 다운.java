class Solution {
    public int[] solution(int target) {
        // target을 만들 수 있는 경우 
        // dp[i][0]: i점을 만들 수 있는 최소 다트 수, 
        // dp[i][1]: 싱글/불 수 
        int[][] dp = new int[target + 1][2];
      
        for (int i=1; i<=target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            dp[i][1] = 0;
        }
      
        // 찾으면서 기록하는 score: 1~target
        for (int score = 1; score <= target; score++) {
       		for (int single=1; single<=20; single++) {
               	if (score >= single && dp[score - single][0] != Integer.MAX_VALUE) {
                   	update(dp, score, dp[score - single][0] + 1, dp[score - single][1] + 1); 
                } 
            }
			for (int num=1; num<=20; num++) {
                int doubleScore = num * 2;
                if (score >= doubleScore && dp[score - doubleScore][0] != Integer.MAX_VALUE) {
                    update(dp, score, dp[score - doubleScore][0] + 1, dp[score - doubleScore][1]);
                }
            }            
			for (int num=1; num<=20; num++) {
                int tripleScore = num * 3;
                if (score >= tripleScore && dp[score - tripleScore][0] != Integer.MAX_VALUE) {
                    update(dp, score, dp[score - tripleScore][0] + 1, dp[score - tripleScore][1]);
                }
            }
            
            if (score >= 50 && dp[score - 50][0] != Integer.MAX_VALUE) {
                update(dp, score, dp[score - 50][0] + 1, dp[score - 50][1] + 1);
            }
        }
        
        return dp[target];
    }
    private void update(int[][] dp, int score, int darts, int singleOrBull) {
        // 현재까지 알려진 다트 수보다 더 작다면 다트 수 갱신
       	if (darts < dp[score][0]) { 
            dp[score][0] = darts;
            dp[score][1] = singleOrBull;
        } else if (darts == dp[score][0] && singleOrBull > dp[score][1]) { // 현재까지 알려진 single/bull 방식으로 던진 다트 수 보다 더 크다면 다트 수 갱신 
            dp[score][1] = singleOrBull;
        }
    }
}