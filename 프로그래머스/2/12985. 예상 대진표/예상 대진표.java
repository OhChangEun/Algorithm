import java.util.*;

class Solution
{
    public int solution(int n, int a, int b)
    {

        int round = 0;
        round = (int)(Math.log(n) / Math.log(2));
        System.out.println(round);
       
        //a = Math.min(a, b);
        //b = Math.max(a, b);
        
        int answer = 0;
        while (answer <= round) {
            // 승리했을 때 부여받는 번호  
            a = (a+2-1)/2; // 올림 계산 
            b = (b+2-1)/2; 
       
            answer++;
            
            if (a==b) {
                break;
            }
        }

        return answer;
    }
}