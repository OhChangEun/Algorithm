class Solution {
    public long solution(int[] sequence) {
        int n = sequence.length; 
        if (n == 1) {
            return Math.abs(sequence[0]);
        }
        
        int[] posArr = sequence.clone(); // 1, -1, 1, ... 을 곱하는 배열 
        int[] negArr = sequence.clone(); // -1, 1, -1, ... 을 곱하는 배열 
       
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) { // 짝수 인덱스에 대해 
                posArr[i] *= -1;
            } else {
                negArr[i] *= -1;
            }
        }
                
        long maxResPos = getMaxValue(posArr);
        long maxResNeg = getMaxValue(negArr);
        
        return Math.max(maxResPos, maxResNeg);
    }
    
    private void print(int[] arr) {
        for (int num: arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    private long getMaxValue(int[] arr) {
        int arrSize = arr.length; 
        
        long maxRes = 0;
        long[] dp = new long[arrSize]; 
        dp[0] = arr[0];
        for (int i = 1; i < arrSize; i++) {
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
            maxRes = Math.max(maxRes, dp[i]);
        }
        
        return maxRes;
    }
}