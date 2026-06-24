class Solution {
    public int solution(int n) {

        // right++ 하다가 n을 넘어서면 left 당기기 
        // n이랑 같아지면 count 세고, 다음 right 
        int left = 1; 

        int sum = 0;
        int cnt = 0;
        for (int right = 1; right <= n; right++) {
            sum += right; 
            
            while (sum > n) {
                sum -= left; 
                left++;
            }
            
            if (sum == n) {
                // print(left);
                cnt++; 
            }
        }
            
        return cnt;
    }
    
    private void print(Object o) {
        System.out.println(o);
    }
}