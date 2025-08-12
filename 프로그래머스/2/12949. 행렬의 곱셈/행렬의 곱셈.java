class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = {};
       
        int rows = arr1.length;
        int cols = arr2[0].length;
        
        System.out.println(cols + " " + rows);
        
        answer = new int[rows][cols];
        
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                for (int k=0; k<arr2.length; k++) {
                	answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        
        return answer;
    }
}