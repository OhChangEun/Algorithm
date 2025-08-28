import java.util.*;

class Solution {
   	int n, m; 
   	int[][] distance; 
	int[] startP;
    int[] endP;
    int[] dx = { 0, 0, -1, 1 };
    int[] dy = { 1, -1, 0, 0 };
    public int solution(String[] board) {
        int answer = 0;
      	n = board.length;
        m = board[0].length();
      	distance = new int[n][m];
        for (int i=0; i<n; i++){
            Arrays.fill(distance[i], -1);
        }
        
        int startX = 0, startY = 0;
        int endX = 0, endY = 0;
       	for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                char ch = board[i].charAt(j);
                if (ch == 'R') {
                    startY = i;
                    startX = j;
                } else if (ch == 'G') {
                    endY = i;
                    endX = j;
                }
            }
        } 
        
        startP = new int[] {startX, startY};
        endP = new int[] {endX, endY};
        
        int result = bfs(startP, endP, board);
        
        //System.out.println(startX + " " + startY);
        //System.out.println(endX + " " + endY);
        
        return result;
    }
    public int bfs(int[] start, int[] end, String[] board) {
        Queue<int[]> queue = new LinkedList<>();
		queue.offer(start);
        distance[start[1]][start[0]] = 0;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cx = curr[0];
            int cy = curr[1];
            
            for (int i=0; i<4; i++) {
                int nx = cx;
                int ny = cy;
                
                if (cx == end[0] && cy == end[1]) {
                    return distance[cy][cx];
                }
                
                while (true) {
                    int tx = nx + dx[i];
                    int ty = ny + dy[i];
                    
                    if (tx < 0 || tx >= m || ty < 0 || ty >= n) break;
                    if (board[ty].charAt(tx) == 'D') break;
                    
                    nx = tx;
                    ny = ty;
                }
                
                if (distance[ny][nx] == -1) {
                    distance[ny][nx] = distance[cy][cx] + 1;
                    queue.offer(new int[]{nx ,ny});
                }
            }
        }
        return -1;
    }
}