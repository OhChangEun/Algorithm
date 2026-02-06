import java.util.*;

class Solution {
    int n;
    List<List<Integer>> winGraph;
    List<List<Integer>> loseGraph;
    
    public int solution(int n, int[][] results) {
        this.n = n;
        
        winGraph = new ArrayList<>();
        loseGraph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            winGraph.add(new ArrayList<>());
            loseGraph.add(new ArrayList<>());
        }
        
        for (int[] result: results) {
            int u = result[0];
            int v = result[1];
            
            winGraph.get(u).add(v);
            loseGraph.get(v).add(u);
        }
        
        int result = 0;
        for (int i = 1; i <= n; i++) {
            int winCnt = bfs(winGraph, i);
            int loseCnt = bfs(loseGraph, i);
            
            int totalCnt = winCnt + loseCnt;
            if (totalCnt == n - 1) {
                result++;
            }
        }
        
        return result;
    }
    
    private int bfs(List<List<Integer>> graph, int start) {
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;
        
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        
        int cnt = 0; 
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            cnt++;
            
            for (int next: graph.get(curr)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        
        return cnt - 1; // 시작 노드 제외
    }
}