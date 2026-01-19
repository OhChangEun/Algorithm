import java.util.*;

class Solution {
    int n;
    Map<Integer, List<Integer>> graph; 
    Set<Integer> visited; 
    
    int oddEvenTreeCnt = 0; 
    int reverseOddEvenTreeCnt = 0;
    
    public int[] solution(int[] nodes, int[][] edges) {
        n = nodes.length; 
        graph = new HashMap<>();
       	
        for (int node: nodes) {
            graph.put(node, new ArrayList<>());
        }
        
        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        visited = new HashSet<>();
        // 모든 노드에 대해 시작점을 다르게 가져가야 한다.
        for (int node: nodes) {
            if (!visited.contains(node)) {
                List<Integer> treeList = new ArrayList<>(); 
                dfs(node, treeList); 
                check(treeList);
            }
        }
        
        return new int[] {oddEvenTreeCnt, reverseOddEvenTreeCnt};
    }
    
    private void check(List<Integer> treeList) {
        int oddEvenCnt = 0;
        int reverseOddEvenCnt = 0;
        
        // 각 노드를 루트라 할 때 
        for (int node: treeList) {
            int childCnt = graph.get(node).size();
            
            // 홀짝 함수 개수 
            if (isEven(node) == isEven(childCnt)) {
                oddEvenCnt++;
            } else { // 역홀짝 함수 개수 
                reverseOddEvenCnt++;
            }
        }
        
        if (oddEvenCnt == 1) oddEvenTreeCnt++;
        if (reverseOddEvenCnt == 1) reverseOddEvenTreeCnt++;   
    }
    
    private void dfs(int node, List<Integer> treeList) {
        visited.add(node); 
        treeList.add(node);
        
        for (int next: graph.get(node)) {
            if (!visited.contains(next)) {
                dfs(next, treeList);
            }
        }
    }
    
    private boolean isEven(int num) {
        return num % 2 == 0 ? true : false;
    }
}