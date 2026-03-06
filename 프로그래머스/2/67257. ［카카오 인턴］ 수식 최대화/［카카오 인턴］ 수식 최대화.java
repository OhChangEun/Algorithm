import java.util.*;

class Solution {
    char[][] priorities = {
        {'+', '-', '*'},
        {'+', '*', '-'},
        {'-', '+', '*'},
        {'-', '*', '+'},
        {'*', '+', '-'},
        {'*', '-', '+'},
    };
    
    public long solution(String expression) {        
        List<String> originTokens = getTokens(expression);
        
        long maxValue = 0;
        for (char[] priority: priorities) {
            List<String> tokens = new ArrayList<>(originTokens);
            
            for (char op: priority) {
                for (int i = 1; i < tokens.size(); i++) {
                    if (tokens.get(i).equals(String.valueOf(op))) {
                        long a = Long.parseLong(tokens.get(i - 1));
                        long b = Long.parseLong(tokens.get(i + 1));
                        
                        long result = calc(op, a, b);
                        
                        tokens.remove(i + 1);
                        tokens.remove(i);
                        tokens.remove(i - 1);
                        tokens.add(i - 1, String.valueOf(result));
                        i--;
                    }
                }
            }
            
            maxValue = Math.max(maxValue, Math.abs(Long.parseLong(tokens.get(0))));
        }
        
        return maxValue;
    }
    
    private Long calc(char op, long a, long b) {
        switch (op) {
            case '+':
                return a + b; 
            case '-':
                return a - b; 
            case '*':
                return a * b; 
        }
        
        return 0L;
    }
    
    private List<String> getTokens(String str) {
        List<String> tokens = new ArrayList<>();
        
        StringBuilder sb = new StringBuilder();
        for (char ch: str.toCharArray()) {
            if (Character.isDigit(ch)) {
                sb.append(ch - '0');
            } else {
                tokens.add(sb.toString());
                sb = new StringBuilder();
                tokens.add(String.valueOf(ch));
            }
        }
        tokens.add(sb.toString());
        
        return tokens;
    }
}