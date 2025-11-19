import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> clothesMap = new HashMap<>();
        for (String[] cloth: clothes) {
            String name = cloth[0];
            String type = cloth[1];
            clothesMap.put(type, clothesMap.getOrDefault(type, 0) + 1);
        } 
        // System.out.println(clothesMap);
       
        int result = 1;
        for (String cloth: clothesMap.keySet()) {
            result *= clothesMap.get(cloth) + 1;      
        }
        
        return result - 1;
    }
}