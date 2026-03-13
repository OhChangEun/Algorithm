import java.util.*;

class Solution {
    int row; 
   	int col;	
    List<List<Integer>> candidateKeys = new ArrayList<>();
    
    public int solution(String[][] relation) {
        row = relation.length;
		col = relation[0].length;  
        
        for (int size = 1; size <= col; size++) {
           	dfs(relation, new ArrayList<>(), size, 0); 
        }
        
        return candidateKeys.size();
    }
    
    private void dfs(String[][] relation, List<Integer> comb, int size, int start) {
        if (comb.size() == size) {
            
            // 1. 최소성 보장
            for (List<Integer> key: candidateKeys) {
               if (comb.containsAll(key)) 
                   return; 
            }
            
            // 2. 유일성 보장 
          	Set<String> set = new HashSet<>();
            for (String[] rows: relation) {
                StringBuilder sb = new StringBuilder();
                
                for (int idx: comb) {
                    sb.append(rows[idx]).append(",");
                }
                
                set.add(sb.toString());
            }
            
            if (set.size() == row) {
                candidateKeys.add(new ArrayList<>(comb));
            }
            
            // System.out.println(comb);
            return; 
        }
        
        for (int i = start; i < col; i++) {
            comb.add(i);
            dfs(relation, comb, size, i + 1);
            comb.remove(comb.size() - 1);
        }
    }
}