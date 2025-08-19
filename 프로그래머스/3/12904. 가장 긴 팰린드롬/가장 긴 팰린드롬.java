class Solution
{
    public int getPalindromeLength(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
   		return right - left - 1; 
    }
    public int solution(String s)
    {
    	int size = s.length(); 
        
        int maxLen = 1;
        for (int i=0; i<size; i++) {
           	maxLen = Math.max(maxLen, getPalindromeLength(s, i, i));
            maxLen = Math.max(maxLen, getPalindromeLength(s, i, i+1));
        }
        
        return maxLen;
    }
}