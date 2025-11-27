import java.util.*;

class Solution {
    class Node { 
        int x, y, dir, cost; 
        public Node (int x, int y, int dir, int cost) { 
            this.x = x; 
            this.y = y; 
            this.dir = dir; 
            this.cost = cost; 
        }
    } 
    // 0: 상
    // 1: 하
    // 2: 좌
    // 3: 우
    int[] dx = new int[] {0, 0, -1, 1};
    int[] dy = new int[] {-1, 1, 0, 0};
    // 각 방향에 따른 최소값
    int[][][] cost;
    
    public int solution(int[][] board) {
        int n = board.length;
        cost = new int[n][n][4]; 
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        } 
        
        Queue<Node> queue = new LinkedList<>(); 
        queue.offer(new Node(0, 0, 1, 0));
        queue.offer(new Node(0, 0, 3, 0));
       
        // 오른쪽, 아래 방향으로 시작
        cost[0][0][1] = 0; 
        cost[0][0][3] = 0; 

        int minCost = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            // 도착지에 도착하면 방향에 상관없이 x,y 좌표를 통해 최소 비용 구하기  
            if (curr.x == n-1 && curr.y == n-1) {
                minCost = Math.min(minCost, curr.cost);
            }
            for (int dir=0; dir<4; dir++) {
                int nx = curr.x + dx[dir]; 
                int ny = curr.y + dy[dir]; 
               
                if (nx < 0 || ny < 0 || nx > n-1 || ny > n-1) continue; // 막다른 길 
                if (board[nx][ny] == 1) continue; // 벽
                
                int newCost = 0;
                if (dir == curr.dir) {
                    newCost = curr.cost + 100;
                } else {
                    newCost = curr.cost + 600; 
                }
               
                if (newCost < cost[nx][ny][dir]) {
                    cost[nx][ny][dir] = newCost;
                    queue.offer(new Node(nx, ny, dir, newCost));
                }
            } 
        }
        return minCost;
    }
}