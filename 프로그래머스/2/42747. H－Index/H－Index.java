import java.util.*;

class Solution {
    public int solution(int[] citations) {
        // 중간값 찾기
        Arrays.sort(citations);
 
        int size = citations.length;
        
        int left = 0;
        int right = citations[size-1];
       
        int h = 0;
        while (left <= right) {
            int mid = (left + right)/2;
           	
            if (isH(citations, mid)) { // h번 인용된 논문이 h번 이상일 때
                left = mid + 1;
                h = mid;
            } else {
                right = mid - 1;
            }
        }
        
        
        return h;
    }
    public boolean isH(int[] arr, int mid) {
       	int count = 0; 
        for (int num: arr) {
           	if (num >= mid) // mid번 이상 논문 인용 
                count++;
        }
        // System.out.println(count + " " + mid);
      	if (count >= mid) return true; 
    
        return false;
    }
}