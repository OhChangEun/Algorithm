import java.util.*;

class Solution {
    Map<Long, Long> map; 
    
    public long[] solution(long k, long[] room_number) {
        int n = room_number.length; 
        long[] answer = new long[n];

        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            answer[i] = find(room_number[i]);
        }
        
        return answer;
    }
    
    private long find(long x) {
        if (!map.containsKey(x)) {
            map.put(x, x + 1);
            return x; 
        }
        
        long next = find(map.get(x));
        map.put(x, next);
        
        return next;
    }
}