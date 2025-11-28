import java.util.*;

class Solution
{
    public int getPalindromeLength(String str, int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        } 
        return right - left - 1;
    } 
    public int solution(String s)
    {
        int maxLength = 1; 
        for (int i=0; i<s.length(); i++) {
            maxLength = Math.max(maxLength, getPalindromeLength(s, i, i)); 
            maxLength = Math.max(maxLength, getPalindromeLength(s, i, i + 1)); 
        } 
        return maxLength;
    }
}