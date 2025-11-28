class Solution {
    public int solution(int num) {
        int count = 0; 
        while (true) {
            if (num == 1) break;
            
            if (num % 2 == 0) {
                num /= 2;
            } else if (num % 2 == 1) {
                num *= 3; 
               	num++; 
            }
            count++;
        	if (count >= 500 && num != 1) return -1;
        }
        
        return count;
    }
}