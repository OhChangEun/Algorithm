import java.util.*;

class Solution {
    final int BASE = 26;
    
    public String solution(long n, String[] bans) {
       	long[] bans26th = new long[bans.length]; // 26진수로 나타낸 벤 주문서 목록 
       	for (int i = 0; i < bans.length; i++) { 
            bans26th[i] = transform(bans[i]); 
        }
        
        Arrays.sort(bans26th);
        for (long ban: bans26th) {
            // 현재 n번째 주문서보다 작은 주문을 벤할 때, 
            // 주문서 하나 뒤로 밀기
            // System.out.print(ban + " ");
            if (ban <= n) {
                n++; 
            } else {
                break;
            }
        }
      
        String result = find(n);
    	return result;    
    }
    
    private String find(long n) {
       	StringBuilder sb = new StringBuilder();
        
        while (n > 0) {
            // 0이 없는 26진법이므로
            long remainder = n % BASE; 
            
            if (remainder == 0) {
                sb.append('z');
                n = n / BASE - 1;
            } else {
                sb.append((char)('a' + remainder - 1));
                n = n / BASE; 
            }
        }
        
        return sb.reverse().toString();
    }
    
    private long transform(String str) {
        long sum = 0;
        for (char ch: str.toCharArray()) {
           	sum = sum * BASE + ch - 'a' + 1;
        }
        return sum;
    }
}