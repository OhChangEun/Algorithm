class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while (storey > 0) {
            int num = storey % 10;
            storey /= 10; 
            
            if (num < 5) {
                answer += num; 
            } else if (num > 5) {
                answer += 10 - num;
                storey += 1;
            } else {
                int next = storey % 10;
                if (next >= 5) {
                    storey += 1; 
                }
                
                answer += 5;
            }
        }
        return answer;
    }
}