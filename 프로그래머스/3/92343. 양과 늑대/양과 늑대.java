import java.util.*;

class Solution {
    List<List<Integer>> graph; 
    int[] info;
    int maxSheepCnt = 0;
    
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
        
        int start = 0; // 시작: 0번 노드 
        List<Integer> candidates = new ArrayList<>();
        candidates.add(start);
        
        int sheep = 0, wolf = 0;
        dfs(start, sheep, wolf, candidates);
        
        return maxSheepCnt;
    }
    
    private void dfs(int curr, int sheep, int wolf, List<Integer> candidates) {
        if (info[curr] == 0) sheep++;
        else wolf++;
        
        if (sheep <= wolf) 
            return; 
        
        maxSheepCnt = Math.max(maxSheepCnt, sheep);
        
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