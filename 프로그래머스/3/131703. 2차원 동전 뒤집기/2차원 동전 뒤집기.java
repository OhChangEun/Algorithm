class Solution {
    int n, m;
    int result = Integer.MAX_VALUE;
    
    public int solution(int[][] beginning, int[][] target) {
        n = beginning.length; 
        m = beginning[0].length;
       
        dfs(beginning, target, 0, 0);
        
        // print(beginning);
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    
    private void dfs(int[][] beginning, int[][] target, int index, int depth) {
        // 최솟값을 넘어서는 dfs는 더이상 보지 않음 
        if (depth >= result) 
            return;
       
        // 발견하면 결과 갱신 및 종료 
        if (isSame(beginning, target)) {
            result = Math.min(result, depth); 
            return;
        }
       
        // 해당 인덱스를 넘어서면 뒤집는게 의미가 없음
        if (index >= n + m) 
            return; 
    
        // 현재 인덱스(row나 col)을 뒤집지 않는 경우
        dfs(beginning, target, index + 1, depth);
        
        // 현재 인덱스(row나 col)을 뒤집는 경우
        flip(beginning, index);
        dfs(beginning, target, index + 1, depth + 1);
        flip(beginning, index);
    }
    
    private void flip(int[][] arr, int index) {
        if (index < n) {
            for (int i = 0; i < m; i++) {
                arr[index][i] = 1 - arr[index][i];
            }
        } else {
            int col = index - n;
            for (int i = 0; i < n; i++) {
                arr[i][col] = 1 - arr[i][col];
            }
        } 
    }
    
    private boolean isSame(int[][] arr1, int[][] arr2) {
        int n = arr1.length;
        int m = arr1[0].length; 
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr1[i][j] != arr2[i][j]) {
                    return false; 
                }
            }
        }
        return true; 
    }
}