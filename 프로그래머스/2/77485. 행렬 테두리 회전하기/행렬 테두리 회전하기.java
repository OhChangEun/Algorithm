class Solution {
    int[][] map; 
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int querySize = queries.length; 
        
        // 1) 초기화 
        map = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = i * columns + j + 1;
            }
        }
        
        // print(map);
        
        // 2) 각 query에 맞게 회전 및 최소값 기록 
        int[] result = new int[querySize];
        for (int i = 0; i < querySize; i++) {
            int sY = queries[i][0] - 1; // 0-based
            int sX = queries[i][1] - 1;
            int eY = queries[i][2] - 1;
            int eX = queries[i][3] - 1; 
            
            int min = rotate(sY, sX, eY, eX);
            result[i] = min;
        }
        return result;
    }
    
    private int rotate(int sY, int sX, int eY, int eX) {
        int minValue = Integer.MAX_VALUE; 
        int start = map[sY][sX]; 
        minValue = Math.min(minValue, start);

        for (int j = sY; j < eY; j++) {
            int next = map[j + 1][sX];
            map[j][sX] = next; 
            minValue = Math.min(minValue, next);
        }
        
        for (int i= sX; i < eX; i++) {
            int next = map[eY][i + 1];
            map[eY][i] = next; 
            minValue = Math.min(minValue, next);
        }
        
        for (int j= eY; j > sY; j--) {
            int prev = map[j - 1][eX];
            map[j][eX] = prev; 
            minValue = Math.min(minValue, prev);
        }
        
        for (int i= eX; i > sX; i--) {
            int prev = map[sY][i - 1];
            map[sY][i] = prev; 
            minValue = Math.min(minValue, prev);
        }
        
        map[sY][sX + 1] = start; // 미리 기록해둔 첫번째 값

        return minValue;
    }
    
    private void print(int[][] arr) {
        for (int[] row: arr) {
            for (int num: row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}