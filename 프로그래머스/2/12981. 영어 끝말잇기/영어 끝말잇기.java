import java.util.*;

class Solution {
    // 앞 단어랑 다르거나
    // 앞서 나온 단어를 한번 더 사용하면 끝 
    public int[] solution(int n, String[] words) {

        Map<String, Integer> map = new HashMap<>();
        
        int loseIdx = 0; 
        int loseTime = 0; 
        
        for (int i = 0; i < words.length - 1; i++) {
            String currWord = words[i]; 
            String nextWord = words[i + 1]; 
            
            char last = currWord.charAt(currWord.length() - 1);
            char first = nextWord.charAt(0);
            
            map.put(currWord, 1);
            if (last != first || map.getOrDefault(nextWord, 0) == 1) {
                loseIdx = (i + 1) % n + 1; 
                loseTime = (i + 1) / n + 1; 
                break;
            }
        }
        
        return new int[] {loseIdx, loseTime};
    }
}