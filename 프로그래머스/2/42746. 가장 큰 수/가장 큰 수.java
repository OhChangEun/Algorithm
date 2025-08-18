import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
      
        int n = numbers.length;
        String[] numStrs = new String[n];
        for (int i=0; i<n; i++) {
           	numStrs[i] = Integer.toString(numbers[i]); 
        	// System.out.println(numStrs[i]);
        }
        
        Arrays.sort(numStrs, (a, b) -> (b+a).compareTo(a+b));
        
        if (numStrs[0].equals("0")) return "0";
        
        StringBuilder sb = new StringBuilder();
        for (String s: numStrs) {
            sb.append(s);
        }
        
        return sb.toString();
    }
}