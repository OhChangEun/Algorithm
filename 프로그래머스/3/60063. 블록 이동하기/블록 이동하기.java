import java.util.*;

class Solution {
    int n;
    int[][] board;
    int[] dx = { 0, 0, -1, 1 };
    int[] dy = { -1, 1, 0, 0 };
    
    class Robot {
        int x1, y1; 
        int x2, y2; 
        int dist;
        public Robot(int x1, int y1, int x2, int y2, int dist) {
           	if (x1 > x2 || x1 == x2 && y1 > y2) {
                this.x1 = x2; 
               	this.y1 = y2; 
                this.x2 = x1; 
                this.y2 = y1;
            } else {
                this.x1 = x1; 
               	this.y1 = y1; 
                this.x2 = x2; 
                this.y2 = y2;
            }
            this.dist = dist; 
        }
        public String key() {
           	return x1 + "," + y1 + "," + x2 + "," + y2; 	 
        }
    }
    public int solution(int[][] board) {
   		n = board.length;
        this.board = board; 
        int result = bfs(board); 
        return result; 
    }
    private boolean canMove(int x, int y) {
       	return x >= 0 && y >= 0 && x < n && y < n && board[x][y] == 0; 
    }
    private int bfs(int[][] board) {
        Robot start = new Robot(0, 0, 0, 1, 0);
        
        Queue<Robot> queue = new LinkedList<>(); 
        queue.offer(start);
        
    	Set<String> visited = new HashSet<>();
       	visited.add(start.key());
        
        while (!queue.isEmpty()) {
            Robot curr = queue.poll(); 
          	int cx1 = curr.x1; 
            int cy1 = curr.y1; 
          	int cx2 = curr.x2; 
            int cy2 = curr.y2; 
            
            if ((cx1 == n - 1 && cy1 == n -1) || (cx2 == n - 1 && cy2 == n - 1)) {
                return curr.dist; 
            }
            
            for (int d = 0; d < 4; d++) {
                int nx1 = cx1 + dx[d];
                int ny1 = cy1 + dy[d];
                int nx2 = cx2 + dx[d];
                int ny2 = cy2 + dy[d];
                
                if (canMove(nx1, ny1) && canMove(nx2, ny2)) {
                    Robot next = new Robot(nx1, ny1, nx2, ny2, curr.dist + 1);
                    if (visited.add(next.key())) {
                    	queue.offer(next);
                    }
                }
            }
            
            if (cy1 == cy2) {
                for (int d: new int[] { -1, 1 }) {
               		if (canMove(cx1, cy1 + d) && canMove(cx2, cy2 + d)) {
                        Robot r1 = new Robot(cx1, cy1, cx1, cy1 + d, curr.dist + 1);
                        Robot r2 = new Robot(cx2, cy2, cx2, cy2 + d, curr.dist + 1);
                        
                        if (visited.add(r1.key())) queue.offer(r1);
                        if (visited.add(r2.key())) queue.offer(r2);
                    }
                }
            } else {
                for (int d: new int[] { -1, 1 }) {
               		if (canMove(cx1 + d, cy1) && canMove(cx2 + d, cy2)) {
                        Robot r1 = new Robot(cx1 + d, cy1, cx1, cy1, curr.dist + 1);
                        Robot r2 = new Robot(cx2 + d, cy2, cx2, cy2, curr.dist + 1);
                        
                        if (visited.add(r1.key())) queue.offer(r1);
                        if (visited.add(r2.key())) queue.offer(r2);
                    }
                }
            }
        }
        
        return -1; 
    }
}