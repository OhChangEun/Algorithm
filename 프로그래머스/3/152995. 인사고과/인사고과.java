import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int myScore1 = scores[0][0];
        int myScore2 = scores[0][1]; 
        int size = scores.length;
        
        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0]) 
                return a[1] - b[1];
            return b[0] - a[0];
        });
       
        // 인센티브 못받는 사람 제거
        int max = scores[0][1];
        for (int i=0; i<size; i++) {
            if (scores[i][1] < max) {
               	// 원호 점수라면 
                if (scores[i][0] == myScore1 && scores[i][1] == myScore2) {
                    return -1;
                } else { // 제거 
                    scores[i][0] = -1;
                    scores[i][1] = -1;
                }
            } else {
                max = scores[i][1];
            }
        }
        
        // 원호보다 점수가 높은 사람 숫자 세기 
       	int rate = 1; 
        for (int i=0; i<size; i++) {
           	if (scores[i][0] + scores[i][1] > myScore1 + myScore2) {
    			rate++;            
            }
        }
        
        return rate;
    }
}