import java.util.*;

class Solution {
    boolean solution(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
      
        for (char ch: s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == '(' && ch == ')') {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        
        return stack.isEmpty(); 
    }
    
    private void print(Object o) {
        System.out.println(o);
    }
}