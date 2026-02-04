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
        long maxReward = 0; 
        for (char[] prior: priorities) { // 모든 연산자 경우의 수에 대해 
            List<String> tokens = new ArrayList<>(originTokens);
            
            for (char op: prior) { // 각 우선순위에 따른 연산자
                for (int i = 0; i < tokens.size(); i++) {
                    if (tokens.get(i).equals(String.valueOf(op))) { // 해당 연산자를 만난 경우 
                        long left = Long.parseLong(tokens.get(i - 1)); // 피연산자 1
                        long right = Long.parseLong(tokens.get(i + 1)); // 피연산자 2
                         
                        long result = calc(op, left, right); // 연산 결과 
                        tokens.remove(i + 1); // 기존 토큰 삭제 
                        tokens.remove(i); 
                        tokens.remove(i - 1);
                        
                        tokens.add(i - 1, String.valueOf(result));
                        i--;
                    }
                }
            }
            
            maxReward = Math.max(maxReward, Math.abs(Long.parseLong(tokens.get(0)))); // 남은 숫자로 최대값 갱신 

        }
        
        return maxReward;
    }
    
    private void print(List<String > tokens) {
        for (String token: tokens) {
            System.out.print(token + " ");
        }
        System.out.println();
    }
    
    private long calc(char ch, long op1, long op2) {
        long result = 0; 
        switch (ch) {
            case '+': 
                result = op1 + op2;
                break;
            case '-': 
                result = op1 - op2;
                break;
            case '*': 
                result = op1 * op2;
                break;
        }
        return result;
    }
    
    private List<String> getTokens(String str) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        for (char ch: str.toCharArray()) {
            if (Character.isDigit(ch)) { // 숫자면 연산자 나오기 전까지 값 추가 
                sb.append(ch);
            } else {
                tokens.add(sb.toString());
                sb = new StringBuilder();
                tokens.add(String.valueOf(ch)); 
            }
        }
        
        tokens.add(sb.toString()); // 마지막은 연산자가 없으므로 
        
        return tokens;
    }
}