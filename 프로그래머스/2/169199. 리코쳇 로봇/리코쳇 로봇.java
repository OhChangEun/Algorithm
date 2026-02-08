import java.util.*;

class Solution {
   	int n, m; 
   	int[][] dists; 
    int sy, sx; 
    int ey, ex; 
    
    int[] dx = { 0, 0, -1, 1 };
    int[] dy = { 1, -1, 0, 0 };
    
    public int solution(String[] board) {
      	n = board.length;
        m = board[0].length();
        
      	dists = new int[n][m];
        for (int i = 0; i < n; i++){
            Arrays.fill(dists[i], -1);
        }
        
       	for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = board[i].charAt(j);
                if (ch == 'R') {
                    sy = i;
                    sx = j;
                } else if (ch == 'G') {
                    ey = i;
                    ex = j;
                }
            }
        } 
        
        int result = bfs(board);
        
        return result;
    }
    public int bfs(String[] board) {
        Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {sy, sx});
        dists[sy][sx] = 0;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cy = curr[0];
            int cx = curr[1];
            
            for (int i=0; i<4; i++) {
                int nx = cx;
                int ny = cy;
                
                if (cy == ey && cx == ex) {
                    return dists[cy][cx];
                }
                
                while (true) {
                    int tx = nx + dx[i];
                    int ty = ny + dy[i];
                    
                    if (tx < 0 || tx >= m || ty < 0 || ty >= n) break;
                    if (board[ty].charAt(tx) == 'D') break;
                    
                    nx = tx;
                    ny = ty;
                }
                
                if (dists[ny][nx] == -1) {
                    dists[ny][nx] = dists[cy][cx] + 1;
                    queue.offer(new int[]{ny ,nx});
                }
            }
        }
        return -1;
    }
}