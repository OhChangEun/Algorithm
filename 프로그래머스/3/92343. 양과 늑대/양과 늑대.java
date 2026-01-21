import java.util.*;

class Solution {
    int maxSheep = 0;
    List<List<Integer>> graph; 
    int[] info;
    
    public int solution(int[] info, int[][] edges) {
       	this.info = info;
   		int n = info.length; 
        
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
           	graph.add(new ArrayList<>());
        }
        
        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1]; 
            
            graph.get(u).add(v);
        }
      
        int start = 0; 
        int sheep = 0, wolf = 0;
        List<Integer> candidates = new ArrayList<>();
        candidates.add(start);
        
        dfs(start, sheep, wolf, candidates);
        
        return maxSheep;
    }
    
    private void dfs(int curr, int sheep, int wolf, List<Integer> candidates) {
        if (info[curr] == 0) sheep++; // 양일 때
        else wolf++; // 늑대일 때
        
        if (sheep <= wolf) // 종료 조건 
            return; 
            
        maxSheep = Math.max(maxSheep, sheep); // 최대 양 개수 갱신 
   
      	List<Integer> newCandidates = new ArrayList<>(candidates);	
       	newCandidates.remove(Integer.valueOf(curr));
        
        for (int next: graph.get(curr)) {
       		newCandidates.add(next);     
        }
        
        for (int child: newCandidates) {
            dfs(child, sheep, wolf, newCandidates);
        }
    }
}