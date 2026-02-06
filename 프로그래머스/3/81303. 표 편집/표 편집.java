import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1; 
            next[i] = i + 1;
        }
        next[n - 1] = -1; 
        
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (String input: cmd) {
            char op = input.charAt(0);
            if (op == 'U' || op == 'D') {
                int dx = Integer.parseInt(input.substring(2));
                
                for (int i = 0; i < dx; i++) {
                    k = (op == 'U') ? prev[k] : next[k];
                }         
            } else if (op == 'C') {
                stack.push(k);
                
                if (prev[k] != -1) next[prev[k]] = next[k];
                if (next[k] != -1) prev[next[k]] = prev[k];
                
                if (next[k] != -1) k = next[k]; 
                else k = prev[k];
                
            } else if (op == 'Z') {
                int restore = stack.pop();
                
                if (prev[restore] != -1) next[prev[restore]] = restore; 
                if (next[restore] != -1) prev[next[restore]] = restore; 
            }
        }
        
        StringBuilder sb = new StringBuilder("O".repeat(n));
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            sb.setCharAt(idx, 'X');
        }
        
        return sb.toString();
    }
}