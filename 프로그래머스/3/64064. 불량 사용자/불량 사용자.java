import java.util.*;

class Solution {
    int result = 0; 
    Set<Set<String>> visitedSet; 
    Set<String> visited; 
    
    public int solution(String[] user_id, String[] banned_id) {        
        visited = new HashSet<>();
        visitedSet = new HashSet<>();
        dfs(user_id, banned_id, 0);
        
        // System.out.println(visitedSet);
        return visitedSet.size();
    }
    
    private void dfs(String[] user_id, String[] banned_id, int depth) {
        if (depth == banned_id.length) {
            visitedSet.add(new HashSet<>(visited));
            return; 
        }
        
        for (String user: user_id) {
            if (!visited.contains(user)) {
                if (canBan(user, banned_id[depth])) {
                    visited.add(user);
                    dfs(user_id, banned_id, depth + 1);
                    visited.remove(user);
                }
            } 
        } 
    }
    
    private boolean canBan(String str, String ban) {
        if (str.length() != ban.length()) return false; 
        
        for (int i = 0; i < ban.length(); i++) {
            char ch = ban.charAt(i);
            if (ch != '*' && str.charAt(i) != ban.charAt(i)) {
                return false; 
            }
        }
        
        return true;
    }
}