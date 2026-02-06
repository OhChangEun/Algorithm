class Solution {
    char[] mapping = {'d', 'l', 'r', 'u'};
    
    int[] dy = {1, 0, 0, -1}; // 하, 좌, 우 상(d, l, r, u 순)
    int[] dx = {0, -1, 1, 0};
    
    int n, m;
    int k; 
    int[][] map;
    
    String result;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
       	this.n = n;
       	this.m = m;
        this.k = k; 
        map = new int[n][m];
        
        boolean isValid = dfs(x - 1, y - 1, r - 1, c - 1, "", 0);

        return isValid ? result : "impossible";
    }
    
    private boolean dfs(int y, int x, int r, int c, String str, int depth) {
        if (depth > k) return false; // 이동한 거리가 k보다 크다면 더이상 못감 
        
        int dist = Math.abs(y - r) + Math.abs(x - c); // 현재 위치부터 목적지까지 남은거리 
        int left = k - depth; // k 위치까지 사용할 수 있는 걸음 
        if (dist > left) return false; // 도달 못함
        if ((dist - left) % 2 != 0) return false; // 
        
        if (depth == k) {
            if (y == r && x == c) {
                result = str; 
                return true;
            }
            return false; 
        }
        
        for (int dir = 0; dir < 4; dir++) {
           	int ny = y + dy[dir];
            int nx = x + dx[dir]; 
            
            if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
            
            if (dfs(ny, nx, r, c, str + mapping[dir], depth + 1)) return true;
            
        }
        
        return false;
    }
}