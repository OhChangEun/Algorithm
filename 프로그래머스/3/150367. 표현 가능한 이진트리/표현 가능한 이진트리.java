class Solution {
    public int[] solution(long[] numbers) {
   		int n = numbers.length; 
       	int[] answer = new int[n];
        
        for (int i = 0; i < n; i++) {
           	String binStr = Long.toBinaryString(numbers[i]);
            
            int len = 1;
            int binLen = binStr.length();
            while (len < binLen) {
                len = len * 2 + 1; 
            }
            
            binStr = "0".repeat(len - binLen) + binStr;
            // System.out.println(binStr);
            
            answer[i] = isValidTree(binStr) ? 1 : 0;
        } 
   		return answer;  
    }
    
    public boolean isValidTree(String str) {
        int n = str.length(); 
        if (n == 1) return true;
        int mid = n / 2;
        
        String left = str.substring(0, mid); 
        String right = str.substring(mid + 1); 
        char root = str.charAt(mid);
        if (root == '0') {
        	if (left.contains("1") || right.contains("1")) 
                return false;
        }
        return isValidTree(left) && isValidTree(right);
    }
}