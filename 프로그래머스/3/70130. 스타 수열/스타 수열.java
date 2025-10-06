import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = -1;
       
        int n = a.length;
        if (n < 2) {
            return 0;
        }
        
        // 숫자 개수 세기 
       	Map<Integer, Integer> freq = new HashMap<>();
        for (int num: a) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
      
        //System.out.println(num);
        int maxLen = 0;
        for (int num: freq.keySet()) {
            if (freq.get(num) * 2 <= maxLen) continue; 
       		
            int i = 0; 
            int count = 0;
            while (i < n-1) {
               	if ((a[i] == num || a[i + 1] == num) && a[i] != a[i + 1]) {
                   	count++;
                    i += 2;
                } else {
                    i++;
                }
            }
       
            maxLen = Math.max(maxLen, count * 2);
        }
        
        return maxLen;
    }
}