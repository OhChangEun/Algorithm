import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations); // 오름차순 정렬
        int n = citations.length;
        int hIndex = 0;
        
        for (int i = 0; i < n; i++) {
            int h = n - i; // citations[i] 이상인 논문 수
            if (citations[i] >= h) {
                hIndex = h; 
                break; // 가장 큰 h 찾으면 바로 종료
            }
        }
        
        return hIndex;
    }
}
