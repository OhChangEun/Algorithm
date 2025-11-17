import java.util.*;

class Solution {
  	Set<Set<String>> result = new HashSet<>(); 
    
    public int solution(String[] user_id, String[] banned_id) {
   		backtrack(user_id, banned_id, 0, new HashSet<>());
        return result.size();
    }
    
    public void backtrack(String[] user_id, String[] banned_id, int depth, Set<String> banned) {
        if (depth == banned_id.length) {
           	result.add(new HashSet<>(banned)); 
            return;
        }
        
        for (String user: user_id) {
            if (banned.contains(user)) continue;
            
            if (canBan(user, banned_id[depth])) {
                banned.add(user);
                backtrack(user_id, banned_id, depth + 1, banned);
                banned.remove(user);
            }
        }
    }
    
    public boolean canBan(String str, String ban) {
        if (str.length() != ban.length()) return false; 
        for (int i=0; i<str.length(); i++) {
            if (ban.charAt(i) != '*' && str.charAt(i) != ban.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}