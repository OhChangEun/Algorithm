import java.util.*;

class Solution {
    public int solution(String dirs) {
        int answer = 0;
      
        // 시작 지점
      	int x = 0;
        int y = 0;
        
        Set<String> visited = new HashSet<>();
        
        Map<Character, int[]> map = new HashMap<>();
        map.put('U', new int[] {1, 0});
        map.put('D', new int[] {-1, 0});
        map.put('L', new int[] {0, -1});
        map.put('R', new int[] {0, 1});
     
        for (char d: dirs.toCharArray()) {
            int nx = x + map.get(d)[1];
            int ny = y + map.get(d)[0];
            
            // 방문한 변 
            String path1 = x + "," + y + "->" + nx + "," + ny;
            String path2 = nx + "," + ny + "->" + x + "," + y;
       
            if (nx >= -5 && nx <= 5 && ny >= -5 && ny <= 5) {
            	if (!visited.contains(path1) && !visited.contains(path2)) {
               		visited.add(path1); 
               		visited.add(path2); 
                    answer++;
                }
                x = nx;
                y = ny;
            }
        }
        return answer;
    }
}