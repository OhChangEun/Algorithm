class Solution {
    int n, m; 
    int size;
    
    public boolean solution(int[][] key, int[][] lock) {
        m = key.length; 
        n = lock.length;
        
        size = n + 2 * (m - 1);
        
        int[][] newLock = new int[size][size];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newLock[i + m - 1][j + m - 1] = lock[i][j];
            }
        }
        
        for (int dir = 0; dir < 4; dir++) {
            key = rotate(key);
            //print(key);
            //System.out.println();
            
            for (int i = 0; i < n + m - 1; i++) {
                for (int j = 0; j < n + m - 1; j++) {
                    if (check(newLock, key, i, j)) 
                        return true; 
                }
            }
        }
   
        return false; 
    }
    
        
    private boolean check(int[][] lock, int[][] key, int y, int x) {
        int[][] temp = new int[size][size];
        // newLock 복사본
        for (int i = 0; i < size; i++) {
            temp[i] = lock[i].clone();
        }
        
        // newLock 복사본에 key에 맞게 1채워 넣기 
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                temp[i + y][j + x] += key[i][j];
            }
        }
    
        if (isValid(temp)) return true;
        return false; 
    }
    
    private boolean isValid(int[][] lock) {
        // print(lock);
        for (int i = m - 1; i < n + m - 1; i++) {
            for (int j = m - 1; j < n + m - 1; j++) {
                if (lock[i][j] != 1) 
                    return false; 
            }
        }
        
        return true; 
    }
    
    private void print(int[][] arr) {
        for (int[] row: arr) {
            for (int num: row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        
    }
    
    private int[][] rotate(int[][] key) {
        int[][] rotatedKey = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                rotatedKey[j][m - i - 1] = key[i][j];
            }
        }
        return rotatedKey;
    }
}