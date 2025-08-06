import java.util.*;

class Solution
{
    public int solution(String s)
    {
        Stack<Character> stack = new Stack<>();
   		for (char ch: s.toCharArray()) {
            if (stack.isEmpty()) 
                stack.push(ch);
            else {
                char top = stack.peek();
               	if (ch == top) {
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            }
        } 
        
        int res = stack.isEmpty() ? 1 : 0;
        return res;
    }
    
}