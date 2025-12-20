import java.util.*;

class Solution {
    int maxSheep = 0;
    public int solution(int[] info, int[][] edges) {
        
        int n = info.length; 
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            
            graph.get(u).add(v);
        }

        int start = 0;
        List<Integer> candidates = new ArrayList<>();
        candidates.add(start); 
        
        dfs(info, graph, 0, 0, 0, candidates);
        
        return maxSheep;
    }
    
    public void dfs(int[] info, List<List<Integer>> graph, int curr, int sheep, int wolf, List<Integer> candidates) {
        if (info[curr] == 0) sheep++; 
        else wolf++; 
        
        if (sheep <= wolf) return; 
        maxSheep = Math.max(maxSheep, sheep); 
        
        List<Integer> newCandidates = new ArrayList<>(candidates); 
        newCandidates.remove(Integer.valueOf(curr));
        
        for (int next: graph.get(curr)) {
            newCandidates.add(next);
        }
        
        for (int child: newCandidates) {
            dfs(info, graph, child, sheep, wolf, newCandidates);
        }
    }
}