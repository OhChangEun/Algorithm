import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> kindOfGems = new HashSet<>(); 
        for (String gem: gems) {
            kindOfGems.add(gem);
        }
        int totalKinds = kindOfGems.size();
       
        int left = 0;
        int bestLeft = 0, bestRight = gems.length - 1;
        Map<String, Integer> currGems = new HashMap<>();
        for (int right=0; right<gems.length; right++) {
            String gemName = gems[right];
            currGems.put(gemName, currGems.getOrDefault(gemName, 0) + 1);
            // System.out.println(currGems);
            
            while (currGems.size() == totalKinds) {
                int currLen = right - left; 
                int bestLen = bestRight - bestLeft;
                
                if (currLen < bestLen || (currLen == bestLen && currLen < bestLen)) {
                    bestLeft = left; 
                    bestRight = right; 
                }
                
                String leftGem = gems[left];
                currGems.put(leftGem, currGems.get(leftGem) - 1); // 현재 보석들 중 가장 왼쪽 보석 내려놓기 
                if (currGems.get(leftGem) == 0) currGems.remove(leftGem); // 없으면 map에서 삭제 
                left++; // 현재 left를 이동 
            }
        }
        
        return new int[] {bestLeft + 1, bestRight + 1};
    }
}