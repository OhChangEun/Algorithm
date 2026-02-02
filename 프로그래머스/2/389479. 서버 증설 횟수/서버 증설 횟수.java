import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
      
        // <끝나는 시간, 꺼야 하는 서버 수> 
        Map<Integer, Integer> serverOffMap = new HashMap<>();
        
        // 매 시각마다 게임 이용자 수에 따라 
        // 현재 가용중인 서버 개수를 갱신하고, (갱신할 때 증설 횟수 추가하면 됨) 
        // 부족하면 증설해야한다. 
        
        int curr = 0; // 현재 운영중인 서버 
        for (int i = 0; i < players.length; i++) {
            curr -= serverOffMap.getOrDefault(i, 0); // 현재 시각에 자동으로 꺼지는 서버 개수 빼기 
       		
            int need = players[i] / m; 
            if (need <= curr) continue; // 현재 서버로 커버 가능하면 다음거 확인 
          
            answer += need - curr; // 커버 불가능할 시 필요한만큼 서버 증설 
            // 증설한 시간(i)를 기준으로 끝나는 시간 설정(i + k)설정 
          	serverOffMap.put(i + k, need - curr);  
            curr = need; 
        }
        
        return answer;
    }
}