import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i=0; i<want.length; i++) {
            wantMap.put(want[i], number[i]);
        }
     
        int count = 0;
        int size = discount.length - 10;
        for (int i=0; i<=size; i++) {
        	Map<String, Integer> dscMap = new HashMap<>();
           	for (int j=i; j<i+10; j++) {
                String sale = discount[j];
                dscMap.put(sale, dscMap.getOrDefault(sale,0) + 1);
            }
            
            boolean isSame = true;
            for (String key: wantMap.keySet()) { // 내가 원하는 물품 중 
                if (!dscMap.containsKey(key) || !wantMap.get(key).equals(dscMap.get(key))) {// 내가 원하는 물품과 세일 품목 개수가 다르다면 
                    isSame = false;
                    break;
                }
            }
            if (isSame) 
                count++;
        }
        
        return count;
    }
}