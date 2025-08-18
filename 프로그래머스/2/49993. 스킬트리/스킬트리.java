import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
       
        int count = 0;
        for (String skill_tree: skill_trees) {
            Queue<Character> queue = new LinkedList<>();
            for (char s: skill.toCharArray()) {
                queue.offer(s); // 스킬 순서대로 queue에 
            } 
            boolean isRightOrder = true;
			for (int i=0; i<skill_tree.length(); i++) {
                char s = skill_tree.charAt(i);
            	if (queue.contains(s)) {
                    if (queue.poll() != s) {
                        isRightOrder = false;
                       	break; 
                    }
                }
            } 
            if (isRightOrder) 
                count++;
        }
        
        return count;
    }
}