import java.util.*;

class Solution {
    int len;
    int[] dx = { 0, 0, -1, 1 };
    int[] dy = { 1, -1, 0, 0 };
    
    class Point {
        int y, x; 
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    
    public int[] solution(String[][] places) {
        len = places.length;
        int[] result = new int[len];
        
        for (int i = 0; i < len; i++) {
            result[i] = isGoodDistance(places[i]) ? 1 : 0;
        }
       
        return result;
    }
    
    private boolean isGoodDistance(String[] place) {
        char[][] map = new char[5][5];
        for (int i = 0; i < place.length; i++) {
            map[i] = place[i].toCharArray();
        }
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 'P') { // 각 지점에서 다음 P로 가는 최단 거리를 구함 
                    //map[i][j] = 'S'; // 시작점으로 표시하고 
                    
                    if (canReach(map, i, j)) // 2칸 내에 다른 사람에 도달할 수 있으면 
                        return false;   
                    
                    //map[i][j] = 'P'; // 시작점 원상복구
                }
            }
        }
        
        return true; 
    }
    
    private boolean canReach(char[][] map, int y, int x) {
        int[][] dist = new int[5][5]; 
        for (int i = 0; i < 5; i++) {
            Arrays.fill(dist[i], -1);
        }
        dist[y][x] = 0;
        
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(y, x));
        
        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            int cy = curr.y; 
            int cx = curr.x; 
           
            // 거리가 2 이하일 때 P를 만나면 false 
            if (dist[cy][cx] > 0 && dist[cy][cx] <= 2) {
                if (map[cy][cx] == 'P') {
                    return true; 
                }
            }
            
            for (int dir = 0; dir < 4; dir++) {
                int ny = cy + dy[dir];
                int nx = cx + dx[dir];
                
                if (ny < 0 || ny >= 5 || nx < 0 || nx >= 5) continue; 
                if (map[ny][nx] == 'X') continue; 
                if (dist[ny][nx] != -1) continue;
                
                dist[ny][nx] = dist[cy][cx] + 1; 
                queue.offer(new Point(ny, nx)); 
            }
        }
        
        return false;
    }
}