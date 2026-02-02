class Solution {
    public int solution(int m, int n, String[] board) {
       	char[][] map = new char[m][n];
        
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }
        
        int answer = 0;
        while (true) {
            boolean[][] remove = new boolean[m][n];
            int count = 0;
       
            // 점수 세기 
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char lu = map[i][j]; 
                    if (lu == '.') continue; 
                    
                    char ru = map[i][j + 1]; 
                    char ld = map[i + 1][j]; 
                    char rd = map[i + 1][j + 1]; 
                    
                    if (lu == ru && lu == ld && lu == rd) {
                        remove[i][j] = true;
                        remove[i][j + 1] = true;
                        remove[i + 1][j] = true;
                        remove[i + 1][j + 1] = true;
                    }
                }
            }
            
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
      				if (remove[i][j]) {
                        map[i][j] = '.';
                        count++; 
                    } 
                }
            }
            
            if (count == 0) break; 
            
            answer += count;
            
            for (int j = 0; j < n; j++) {
                int endRow = m - 1; 
                for (int i = m - 1; i >= 0; i--) {
                    if (map[i][j] == '.') continue; 
                    
                    map[endRow][j] = map[i][j];
                    if (endRow != i) {
                        map[i][j] = '.';
                    }
                    endRow--;
                }
            }
        }
        
        return answer;
    }
}