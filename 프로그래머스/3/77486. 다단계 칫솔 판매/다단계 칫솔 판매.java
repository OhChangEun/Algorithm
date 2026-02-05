import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Integer> rewardMap = new HashMap<>(); // <직원, 총급여>
        Map<String, String> parentMap = new HashMap<>(); // <자식, 부모>
        
        int n = enroll.length; 
        for (int i = 0; i < n; i++) {
            String child = enroll[i];
            String parent = referral[i];
            
            parentMap.put(child, parent);
        }
        
        for (int i = 0; i < seller.length; i++) {
            String sellerName = seller[i];
            int reward = amount[i] * 100;
            
            while (!sellerName.equals("-") && reward > 0) { // center가 나올 때까지 
                int parent = reward / 10; // 10%
                int my = reward - parent; // 90%
                
                rewardMap.put(sellerName, rewardMap.getOrDefault(sellerName, 0) + my);
                
                sellerName = parentMap.get(sellerName); 
                reward = parent;
            }
        }
        
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            String sellerName = enroll[i];
            result[i] = rewardMap.getOrDefault(sellerName, 0);
        }
        
        return result;
    }
}