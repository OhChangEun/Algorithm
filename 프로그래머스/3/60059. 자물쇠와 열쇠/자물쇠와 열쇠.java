import java.util.*;
class Solution {
    int M, N; 
    int size; 
    public boolean solution(int[][] key, int[][] lock) {
        M = key.length; 
        N = lock.length; 
       
        size = N + 2 * (M - 1); 
        int[][] newLock = new int[size][size]; 
        
        for (int i = M - 1; i < N + M - 1; i++) {
            for (int j = M - 1; j < N + M - 1; j++) {
                newLock[i][j] = lock[i - M + 1][j - M + 1]; 
            }
        }
        /* 
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                System.out.print(newLock[i][j] + " ");
            }
            System.out.println();
        } */
        
        boolean result = false;
        for (int k = 0; k < 4; k++) {
            key = rotate(key);
            
            for (int i = 0; i < N + M - 1; i++) {
                for (int j = 0; j < N + M - 1; j++) {
                    if (check(newLock, key, i, j)) {
                        result = true;
                    }
                }
            }
        }
        
        return result; 
    }
    public boolean check(int[][] lock, int[][] key, int x, int y) {
        // key 값에 1로 되어있는 값을 lock에 기록하고, 
        // lock을 검사해서 해당 범위가 다 1이면 true, 아니면 false
        int[][] temp = new int[size][size]; 
        for (int i = 0; i < size; i++) {
            temp[i] = Arrays.copyOf(lock[i], size); 
        }
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                temp[x + i][y + j] += key[i][j];
            }
        }
        
        return canSolve(temp);
    }
    
    public boolean canSolve(int[][] lock) {
        boolean result = true;
        for (int i = M - 1; i < N + M - 1; i++) {
            for (int j = M - 1; j < N + M - 1; j++) {
                if (lock[i][j] != 1) {
                    result = false; 
                } 
            }
        }
        return result;
    }
    
    public int[][] rotate(int[][] field) {
        int[][] result = new int[M][M];
        for (int i = 0; i < M; i++) {
           for (int j = 0; j < M; j++) {
                result[j][M - i - 1] = field[i][j]; 
            }
        }
        return result; 
    }
}