import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int myAttitudeScore = scores[0][0];
        int myPeerScore = scores[0][1];
        
        Arrays.sort(scores, (a, b) -> {
            if (a[0] != b[0]) return b[0] - a[0];
            return a[1] - b[1];
        });

        int prevPeerScore = scores[0][1]; 
        for (int i = 1; i < scores.length; i++) {
            if (prevPeerScore > scores[i][1]) { // 두 점수다 작다면
                if (isMe(myAttitudeScore, myPeerScore, scores[i][0], scores[i][1])) { // 완호가 작다면 
                    return -1;
                }
                scores[i][0] = -1;
                scores[i][1] = -1; 
            } else {
                prevPeerScore = scores[i][1];
            }
        }
        
        int rate = 1;
        for (int[] score: scores) {       
           	int total = score[0] + score[1];
            int myScore = myAttitudeScore + myPeerScore;
            if (total > myScore) {
                rate++;
            }
        }
        
        return rate;
    }
    
    private boolean isMe(int a, int b, int c, int d) {
        if (a == c && b == d) {
            return true;
        } 
        return false; 
    }
}