import java.util.*;

class Solution {
    List<List<Integer>> graph; 
    int[] dist; 
    
    public int solution(String begin, String target, String[] words) {
        int n = words.length; 
        
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 첫 단어 + 단어 배열
        List<String> wordsList = new ArrayList<>();
        wordsList.add(begin);
        for (String word: words) {
            wordsList.add(word);
        }
        
        // 찾고자 하는게 없다면 return 
        if (!wordsList.contains(target)) 
            return 0;
        
        // 단어 간 연관관계 매핑 
        int len = wordsList.size();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
            if (isDifferent(wordsList.get(i), wordsList.get(j))) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        
        dist = new int[len];
        int result = bfs(0, wordsList.indexOf(target));
        
        return result;
    }
    
    private int bfs(int start, int target) {
        Arrays.fill(dist, -1);
        dist[start] = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start); 
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            if (curr == target) {
                return dist[target];
            }
            
            for (int next: graph.get(curr)) {
                if (dist[next] == -1) {
                    dist[next] = dist[curr] + 1; 
                    queue.offer(next);
                }
            }
        }
        
        return 0;
    }
    
    private void print(List<String> list) {
        for (String str: list) {
            System.out.print(str + " ");
        }
        System.out.println();
    }
    
    private boolean isDifferent(String a, String b) {
        // 1글자 차이인지 
        int cnt = 0; 
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                cnt++;
                
                if (cnt >= 2) return false; 
            }
        }
        
        return true; 
    }
}