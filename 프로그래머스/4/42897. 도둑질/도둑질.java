class Solution {    
    public int solution(int[] money) {
        int n = money.length; 

        int[] includeFirst = new int[n - 1];
        int[] notIncludeFirst = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            includeFirst[i] = money[i];
            notIncludeFirst[i] = money[i + 1];
        }
       
        int res1 = getMax(includeFirst);
        int res2 = getMax(notIncludeFirst);
        
        return Math.max(res1, res2);
    }
    
    private int getMax(int[] arr) {
        int n = arr.length;
        
        int[] dp = new int[n];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[1], arr[0]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + arr[i], dp[i - 1]);
        }
        
        return dp[n - 1];
    }
    
    private void print(int[] arr) {
        for (int num: arr) {
            System.out.print(num + " ");
        }
    }
}