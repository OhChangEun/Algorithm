import java.util.*;

class Solution {
    final int UP = 0; // 상
    final int DOWN = 1; // 하
    final int LEFT = 2; // 좌 
    final int RIGHT = 3; // 우 
    
    int n; 
    int[] dy = { -1, 1, 0, 0 };
    int[] dx = { 0, 0, -1, 1 };
    
    class Node {
        int y, x; 
        int dir; 
        
        public Node(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }
    
    public int solution(int[][] board) {
        n = board.length;
        
        int result = bfs(board, 0, 0);
        return result;
    }
    
    private int bfs(int[][] board, int y, int x) {
        int minCost = Integer.MAX_VALUE; 
        
        int[][][] costs = new int[n][n][4]; // y, x, 4방향
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(costs[i][j], -1);
            }
        }
        costs[y][x][DOWN] = 0; 
        costs[y][x][RIGHT] = 0; 
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(y, x, DOWN));
        queue.offer(new Node(y, x, RIGHT));
        
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            int cy = curr.y;
            int cx = curr.x; 
            int cDir = curr.dir; 
            
            if (cy == n - 1 && cx == n - 1) {
                int cost = costs[cy][cx][cDir];
                minCost = Math.min(minCost, cost);
            }
            
            for (int dir = 0; dir < 4; dir++) {
                int ny = cy + dy[dir];
                int nx = cx + dx[dir];
                
                if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue; 
                if (board[ny][nx] == 1) continue; 
                
                int newCost = costs[cy][cx][cDir] + (cDir == dir ? 100 : 600);
                if (costs[ny][nx][dir] == -1 || costs[ny][nx][dir] > newCost) { 
                    costs[ny][nx][dir] = newCost; 
                    queue.offer(new Node(ny, nx, dir));
                }
            }
        }
        
        return minCost;
    }
}