import java.util.*;

class Solution {
   
    char[][] map; 
    
    int n, m;
    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};
    
    public int solution(String[] board) {
        n = board.length; 
        m = board[0].length();
        
        map = new char[n][m];
        
        int startY = 0, startX = 0;
        int endY = 0, endX = 0; 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = board[i].charAt(j);
                
                map[i][j] = ch;
                if (ch == 'R') {
                    startY = i; 
                    startX = j; 
                } else if (ch == 'G') {
                    endY = i; 
                    endX = j; 
                }
            }
        } 
        
        int result = bfs(startY, startX, endY, endX);
        return result; 
    }
    
    public int bfs(int startY, int startX, int endY, int endX) {
        int[][] dists = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dists[i], -1);
        }
        dists[startY][startX] = 0;

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {startY, startX});
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cy = curr[0];
            int cx = curr[1]; 
            
            if (cy == endY && cx == endX) {
                return dists[cy][cx];
            }
            
            for (int dir = 0; dir < 4; dir++) {
                int ny = cy; 
                int nx = cx; 
                
                while (true) {
                    int ty = ny + dy[dir]; 
                    int tx = nx + dx[dir]; 
                    
                    // 밖으로 나가는 경우나 벽을 만나는 경우
                    if (ty < 0 || ty >= n || tx < 0 || tx >= m) break; 
                    if (map[ty][tx] == 'D') break; 

                    ny = ty; 
                    nx = tx; 
                }
                            
                if (dists[ny][nx] == -1) {
                    dists[ny][nx] = dists[cy][cx] + 1;
                    queue.offer(new int[] {ny, nx});
                }
            }
        }
        
        return -1;
    }
}