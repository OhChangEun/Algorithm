import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int n = numbers.length; 
        
        int[] answer = new int[n]; 
        Arrays.fill(answer, -1);
        
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            int curr = numbers[i];
            
            while (!stack.isEmpty() && curr > numbers[stack.peek()]) {
                int idx = stack.pop();
                answer[idx] = curr; 
            }
            
            stack.push(i); 
        }
        
        return answer;
    }
}