import java.util.*;

class Solution {
    // 각 이익 계산하는 map 
    // 부모 - 자식 관계 설정하는 map 
    public int[] solution(String[] enrolls, String[] referrals, String[] sellers, int[] amounts) {
        Map<String, Integer> profitMap = new HashMap<>();
        Map<String, String> parentMap = new HashMap<>();
        
        int n = enrolls.length;
        
        for (int i = 0; i < n; i++) {
            String enroll = enrolls[i];
            String referral = referrals[i];
            
            parentMap.put(enroll, referral);
            profitMap.put(enroll, 0);
        }
        
        int sellerNum = sellers.length; 
        for (int i = 0; i < sellerNum; i++) {
            String seller = sellers[i];
            int amount = amounts[i];             
                
            int myProfit = amount * 100; 
            int parentProfit = 0;
            while (!seller.equals("-") && myProfit != 0) {
                parentProfit = myProfit / 10; 
                myProfit -= parentProfit;
                
                profitMap.put(seller, profitMap.getOrDefault(seller, 0) + myProfit); 
                
                seller = parentMap.get(seller);
                myProfit = parentProfit; 
            }
                
        }
        
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = profitMap.get(enrolls[i]);
        }
        
        return result;
    }
    
    private void print(Object o) {
        System.out.println(o);
    }
}