import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
       
        int toBinaryCount = 0;
        int totalZeroCount = 0;
        while (true) {
            System.out.println(s);
            if (s.equals("1")) break;
           
            int zeroCount = 0;
            // 1) 0을 제거한 정수 배열 
            for (char ch: s.toCharArray()) {
                if (ch == '0') {
                    zeroCount++; 
                }
            }

            int c = s.length() - zeroCount; // 1의 개수

            Stack<Integer> stack = new Stack<>();
            while (true) {
                if (c == 1) break;

                stack.push(c%2);
                c = c/2;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(1); // 첫 숫자는 1
            while (!stack.isEmpty()) {
                sb.append(stack.pop());    
            } 
			s = sb.toString();
            System.out.println(sb.toString());
   
            totalZeroCount += zeroCount;
			toBinaryCount++;
        }
        
        // 변환 횟수 and  제거된 모든 0의 개수 배열에 담아 리턴
      	answer = new int[2];
        answer[0] = toBinaryCount;
        answer[1] = totalZeroCount;
        return answer;
    }
}