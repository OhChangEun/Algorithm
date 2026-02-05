import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Map<String, Integer> currGemMap = new HashMap<>(); // 현재 내가 가지고 있는 gem 개수
        Map<String, Integer> gemMap = new HashMap<>(); // 총 gem 개수 
        for (String gem: gems) {
            gemMap.put(gem, gemMap.getOrDefault(gem, 0) + 1);
        }
        
        int gemsCnt = gems.length;
        int totalKind = gemMap.keySet().size(); // 총 보석 종류 개수 
    
        int left = 0;
        int minLen = gemsCnt - left - 1;
        int resLeft = 0, resRight = gems.length;
        
        for (int right = 0; right < gemsCnt; right++) {
            String gem = gems[right];
            currGemMap.put(gem, currGemMap.getOrDefault(gem, 0) + 1);
        
            // 현재 가진게 총 보석 종류 개수와 같아지면 
            // left를 최대한 많이 땅겨서 최소 길이를 구해야 한다. 
            while (left <= right) {
                int currKind = currGemMap.keySet().size(); // 현재 가지고 있는 보석 종류 개수 
                if (currKind < totalKind) break; 
                
                int len = right - left - 1; 
                if (len < minLen) {
                    minLen = len; 
                    resLeft= left; 
                    resRight = right; 
                }
                
                String removeGem = gems[left];
                currGemMap.put(removeGem, currGemMap.get(removeGem) - 1);
                
                if (currGemMap.get(removeGem) == 0) {
                    currGemMap.remove(removeGem);
                }
                // System.out.println(currGemMap);
                left++;
            }
        }
        
        
        return new int[] {resLeft + 1, resRight + 1};
    }
}