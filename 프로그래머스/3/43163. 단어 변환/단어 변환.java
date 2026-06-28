import java.util.*;

class Solution {
    List<List<Integer>> graph;
    int n;
    
    // hit -> hot, 
    // hot -> dot -> dog -> log, cog -> 
    public int solution(String begin, String target, String[] words) {
        n = words.length; 
        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
      
        List<String> wordList = new ArrayList<>();
        wordList.add(begin);
        for (String word: words) {
            wordList.add(word);
        }
        
        if (!wordList.contains(target)) 
            return 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                String from = wordList.get(i);
                String to = wordList.get(j);
                
                if (valid(from, to)) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                } 
            } 
        }
        
        // print(graph);
        
        int min = bfs(0, wordList.indexOf(target));
                   
        return min;  
    }
    
    public int bfs(int start, int target) {
        int[] dists = new int[n + 1]; 
        Arrays.fill(dists, Integer.MAX_VALUE); 
        dists[start] = 0;
        
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(start); 
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            if (curr == target) {
                return dists[curr];
            }
            
            for (int next: graph.get(curr)) {
                if (dists[next] == Integer.MAX_VALUE) {
                    dists[next] = dists[curr] + 1;
                    queue.offer(next);
                }
            }
        }
        
        return 0;
    }
    
    public void print(Object o) {
        System.out.println(o);
    }
    
    public boolean valid(String a, String b) {
        int n = a.length();
       
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i)) 
                cnt++;
            if (cnt > 1) return false; 
        }
        
        return true;
    }
                 
}