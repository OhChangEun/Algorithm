import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
       
        int myScore1 = scores[0][0];
        int myScore2 = scores[0][1];
      	int size = scores.length;
        
        // 근무 태도 점수 내림차순, 동료 평가 오름차순 정렬
        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });
     
        // 다른 사원보다 두 점수가 모두 낮은 경우 인센티브 제외
        int max = scores[0][1];  
        for (int i=1; i<size; i++) {
            if (scores[i][1] < max) {
                // 완호 인지 확인 
                if (scores[i][0] == myScore1 && scores[i][1] == myScore2) {
                    return -1;
                } else {
                    scores[i][0] = -1;
                    scores[i][1] = -1;
                }
            } else {
                max = scores[i][1];
            }
        }
        
        // 완호 등수 매기기
        int rate = 1;
        for (int i=0; i<size; i++) {
            if (scores[i][0] + scores[i][1] > myScore1 + myScore2) {
                rate++;
            }
        }
        
        return rate;
    }
}