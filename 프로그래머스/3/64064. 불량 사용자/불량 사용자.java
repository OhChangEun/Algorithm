import java.util.*;

class Solution {
   	Set<Set<String>> result = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
   		backtrack(user_id, banned_id, 0, new HashSet<>());
 		return result.size();   
    }
   
    private void backtrack(String[] user_id, String[] banned_id, int depth, Set<String> banned) {
        if (depth == banned_id.length) {
           	result.add(new HashSet(banned));
            return;
        }
        
        for (String user: user_id) {
            if (banned.contains(user)) continue; // 이전에 ban된 친구는 확인 x
            
            if (canBan(user, banned_id[depth])) {
               	banned.add(user);
                backtrack(user_id, banned_id, depth + 1, banned);
                banned.remove(user);
            }
        }
    }
    
    
    // 문자열 길이가 같고, banned_id에서 *를 제외한 글자들이 user_id와 순서와 값이 같을 때 
    // 그 문자열이 제대 아이디라고 할 수 있다. 
    private boolean canBan(String user, String ban) {
        if (user.length() != ban.length()) return false;
       	for (int i=0; i<ban.length(); i++) {
            if (ban.charAt(i) != '*' && user.charAt(i) != ban.charAt(i)) 
                return false;
        }
    	return true;
    } 
}