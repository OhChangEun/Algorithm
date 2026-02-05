class Solution
{    
    public int solution(String s) {
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int res1 = getPalindromeLength(s, i, i);
            int res2 = getPalindromeLength(s, i, i + 1);
            
            maxLen = Math.max(maxLen, Math.max(res1, res2));
        }
        return maxLen;
    }
    
    private int getPalindromeLength(String str, int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        
        return right - left - 1;
    }
}