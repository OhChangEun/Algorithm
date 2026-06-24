class Solution {
    public int solution(int n) {        

        for (int num = n + 1; num <= 1000_000; num++) {
            if (countOne(n) == countOne(num)) {
                return num; 
            }
        }
        
        return -1;
    }
    
    private int countOne(int num) {
        String str = Integer.toBinaryString(num);

        int cnt = 0; 
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                cnt++;
            }
        }
        
        return cnt;
    }
    
    private void print(Object o) {
        System.out.println(o);
    }
}