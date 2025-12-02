import java.util.*;

class Solution {
    Map<String, Integer> enrollMap = new HashMap<>();
    Map<String, String> parentMap = new HashMap<>();
    
    public void dfs (String curr, int money) {
        if (curr.equals("-") || money == 0) return;   
        
        int sendMoney = money / 10;
        int myMoney = money - sendMoney;
        enrollMap.put(curr, enrollMap.getOrDefault(curr, 0) + myMoney);
        
        dfs(parentMap.get(curr), sendMoney);
    } 
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;
        for (int i = 0; i < n; i++) {
            enrollMap.put(enroll[i], 0);
            parentMap.put(enroll[i], referral[i]);
        }
        
        for (int i = 0; i < seller.length; i++) {
            String name = seller[i];
            int profit = amount[i] * 100; // 100원 단위
            dfs(name, profit);
        }
     
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = enrollMap.getOrDefault(enroll[i], 0);
        }
        
        return answer;
    }
}