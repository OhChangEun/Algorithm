import java.util.*;

class Solution {
    public int solution(int[][] scores) {
   		int[] myScore = Arrays.copyOf(scores[0], 2); 
      
        Arrays.sort(scores, (a, b) -> {
          	if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });
        
        int maxScore2 = scores[0][1];
        for (int i=1; i<scores.length; i++) {
			if (scores[i][1] < maxScore2) {
                if (scores[i][0] == myScore[0] && scores[i][1] == myScore[1]) return -1;
           		scores[i][0] = -1; 
                scores[i][1] = -1;
            } else {
               	maxScore2 = scores[i][1]; 
            }
        }
       	
        int rate = 1;
       	for (int[] score: scores) {
           	int myTotalScore = myScore[0] + myScore[1]; 
           	int totalScore = score[0] + score[1]; 
            if (myTotalScore < totalScore) rate++;
        } 
        return rate;
    }
}