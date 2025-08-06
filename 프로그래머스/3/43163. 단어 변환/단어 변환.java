import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
      
        int targetIndex = 0;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<words.length+1; i++) {
            graph.add(new ArrayList<>());
        }
      
        boolean isSame = false;
        List<String> list = new ArrayList<>();
        // 첫 노드에 begin
        list.add(begin);
        for (int i=0; i<words.length; i++) { 
            if (words[i].equals(target)) {
                isSame = true;
               	targetIndex = i; 
            }
            list.add(words[i]);
        }
        
        if (!isSame) 
            return 0;
        
        int size = list.size();
        System.out.println(size);
        
        for (int i=0; i<size; i++) {
            for (int j=1; j<size; j++) {
               	if (i == j) continue; 
                char[] word1 = list.get(i).toCharArray();
                char[] word2 = list.get(j).toCharArray();
               
                int count = 0;
                for (int k=0; k<word1.length; k++) {
                    if (word1[k] != word2[k]) {
                        count++;
                    }
                }
                
                // 만약 일치하지 않는 문자가 2개 이하라면 
                // 자기 자신을 제외하고
                if (count < 2) {
                    graph.get(i).add(j);
                    System.out.println(i + " " + j);
                }
            }
        }
        
        System.out.println(targetIndex + 1);
        int distance = bfs(0, targetIndex+1, graph);
        return distance;
    }
    
    public int bfs(int start, int target, List<List<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.size() + 1];
        int[] distance = new int[graph.size() + 1];
      
        queue.add(start);
        visited[start] = true;
        distance[start] = 0;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            System.out.println("curr: " + curr);
            if (target == curr) {
           		return distance[curr];   
            }
            for (int next: graph.get(curr)) {
                if (!visited[next]) {
                    visited[next] = true;
                    distance[next] = distance[curr] + 1;
                    queue.add(next);
                }
            }
        }
        return -1;
    }
}