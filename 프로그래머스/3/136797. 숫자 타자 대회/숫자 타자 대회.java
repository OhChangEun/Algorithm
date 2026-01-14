class Solution {
    public int solution(String numbers) {
        int n = numbers.length();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            int num = numbers.charAt(i) - '0'; 
            nums[i] = num;
        }
        
        int result = getMinTyping(nums);
        return result;
    }
    
    private int getMinTyping(int[] nums) {
        int n = nums.length;
        int[][][] dp = new int[n + 1][10][10];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        
        dp[0][4][6] = 0;
        
        for (int i = 1; i <= n; i++) {
            int curr = nums[i - 1];
            
            for (int left = 0; left < 10; left++) {
                for (int right = 0; right < 10; right++) {
                    if (dp[i - 1][left][right] == Integer.MAX_VALUE) continue; 
                    
                    int prev = dp[i - 1][left][right];
                    if (curr != right) {
                        dp[i][curr][right] = Math.min(dp[i][curr][right], prev + cost(left, curr));
                    }
                    
                    if (curr != left) {
                        dp[i][left][curr] = Math.min(dp[i][left][curr], prev + cost(right, curr));
                    }
                }
            }
        }
        
        int minCount = Integer.MAX_VALUE; 
        for (int left = 0; left < 10; left++) {
            for (int right = 0; right < 10; right++) {
                minCount = Math.min(minCount, dp[n][left][right]);   
            }
        }
        
        return minCount;
    }
    
    private int[] getPos(int num) {
        if (num == 0) 
            return new int[] { 3, 1 };
       
        num--;
        
        return new int[] { num / 3, num % 3 };
    }
    
    private int cost(int from, int to) {
        if (from == to) return 1; 
        
        int[] fromPos = getPos(from); 
        int[] toPos = getPos(to);
        
        int diffY = Math.abs(fromPos[0] - toPos[0]);
        int diffX = Math.abs(fromPos[1] - toPos[1]);
        
        int diag = Math.min(diffY, diffX);
        int straight = Math.abs(diffY - diffX);
        
        return diag * 3 + straight * 2;
    }
    
    private void print(int[] nums) {
        for (int num: nums) {
            System.out.print(num + " ");
        }
    }
}