import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = -1;
       
        String str = s + s; 
        // System.out.println(str);
        
        Stack<Character> stack = new Stack<>();
        char[] charArr = str.toCharArray();
       
        // 0~5까지
        int count = 0;
        int size = s.length();
        for (int i=0; i<size; i++) {
        	boolean isRight = true;
            for (int j=i; j<i+size; j++) {
                char ch = charArr[j];
                if (ch == '(' || ch == '{' || ch == '[')
                    stack.push(ch);
                else {
                    if (stack.isEmpty()) {
                        isRight = false; 
                        break;
                    } else {
                        char b = stack.peek();
                        if (ch == ')') {
                       		if (b == '(') {
                           		stack.pop(); 
                            }    
                        } else if (ch == '}') {
                            if (b == '{') {
                           		stack.pop(); 
                            } 
                        } else if (ch == ']') {
                            if (b == '[') {
                                stack.pop();
                            }
                        }
                    }
                }
            }
            if (isRight && stack.isEmpty())
                count++;
        }
        System.out.println(charArr);
        
        return count;
    }
}