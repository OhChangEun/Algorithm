import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
   		Map<String, Integer> freq = new HashMap<>();
        for (String p: participant) {
            freq.put(p, freq.getOrDefault(p, 0) + 1);
        }
     
        for (String c: completion) {
            freq.put(c, freq.get(c) - 1);
        }
        
        for (String key: freq.keySet()) {
            int value = freq.get(key);
            if (value == 1) 
                return key;
        }
        
        return "";
    }
}