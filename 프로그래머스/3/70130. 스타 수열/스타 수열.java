import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = -1;
        
        int n = a.length; 
        if (n < 2) return 0; 
        
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : a) {
           	freq.put(num, freq.getOrDefault(num, 0) + 1); 
        }
        
        int maxLen = 0;
        for (int star: freq.keySet()) {
            if (freq.get(star) * 2 <= maxLen) continue;
            
            int i = 0;
            int starCount = 0;
            while (i < n - 1) {
                if ((a[i] == star || a[i + 1] == star) && a[i] != a[i + 1]) {
                    i += 2;
                    starCount++;
                } else {
                    i++;
                }
            }
            maxLen = Math.max(maxLen, starCount * 2);
        }
        
        // System.out.println(freq);
        
        return maxLen;
    }
}