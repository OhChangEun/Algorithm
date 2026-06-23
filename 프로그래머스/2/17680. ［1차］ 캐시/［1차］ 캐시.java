import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
    	if (cacheSize == 0) {
            return cities.length * 5;
        }	
        
      	List<String> list = new ArrayList<>(); 
        
        int runTime = 0;
        for (String city: cities) {
            city = city.toLowerCase();
            
            if (isCacheHit(list, city)) {
                list.remove(city);
                list.add(city);
                runTime += 1; 
            } else {
                if (list.size() == cacheSize) {
                    list.remove(0);
                }
                list.add(city);
                runTime += 5; 
            }
        }
        
        // print(list);
        
        
        return runTime;
    }
    
    private boolean isCacheHit(List<String> list, String word) {
        return list.contains(word);
    }
    
    private void print(Object o) {
        System.out.println(o);
    }
}