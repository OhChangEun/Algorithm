import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        List<String> list = new ArrayList<>();
      
        int index = 0;
        list.add(words[0]);
        for (int i=1; i<words.length; i++) {
          	String word = words[i];
            char lastWord = words[i-1].charAt(words[i-1].length()-1);
            char firstWord = words[i].charAt(0);
                //System.out.println(lastWord);
                //System.out.println(firstWord);
            if (list.contains(word) || lastWord != words[i].charAt(0)) { // 이전에 말한 단어거나 
                index = i;
                break;
            }
            list.add(word);
        }
        // System.out.println((index/n)+1);
        
        if (index == 0) {
           	return new int[] {0, 0}; 
        } else {
            return new int[] {(index%n)+1, (index/n)+1};
        }
    }
}