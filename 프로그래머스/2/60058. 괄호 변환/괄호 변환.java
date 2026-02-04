import java.util.*;

class Solution {
    public String solution(String p) {
        String result = findValid(p);
        return result;
    }
    
    private String findValid(String curr) {
        if (isValid(curr)) {
            return curr; 
        }
        
        String[] parts = splitStr(curr);
        String u = parts[0];
        String v = parts[1];
        
        StringBuilder sb = new StringBuilder();
        if (isValid(u)) {
            sb.append(u).append(findValid(v));
        } else {
            sb.append('(');
            sb.append(findValid(v));
            sb.append(')');
            sb.append(reverse(u.substring(1, u.length() - 1))); // 앞뒤 제거한 u 내부 뒤집기        
        }
      
        // System.out.println(u + "   " + v);
        return sb.toString();
    } 
    
    private String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }
        return sb.toString();
    }
    
    // u와 v로 분리
    private String[] splitStr(String str) {
        int open = 0;
        int close = 0; 
        
        String u = "";
        String v = "";
        StringBuilder sb = new StringBuilder();
        for (char ch: str.toCharArray()) {
            if (ch == '(') open++;
            else close++;
            
            sb.append(ch);
            
            if (open != 0 && open == close) {
                int len = sb.length();
                u = sb.toString();
                v = str.substring(len);
                break;
            }
        }
        
        return new String[] {u, v};
    }
    
    private boolean isValid(String str) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char ch: str.toCharArray()) {
            if (ch == ')') {
                if (stack.isEmpty()) return false; 
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        
        return stack.isEmpty();
    }
}