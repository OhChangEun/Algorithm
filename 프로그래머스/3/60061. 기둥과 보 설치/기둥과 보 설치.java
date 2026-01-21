import java.util.*;

class Solution {
    boolean[][] pillars; 
    boolean[][] beams; 
    
    int n;

    public int[][] solution(int n, int[][] build_frame) {
        this.n = n; 
        pillars = new boolean[n + 1][n + 1];
        beams = new boolean[n + 1][n + 1];
                
        for (int[] frame: build_frame) {
            int x = frame[0];
            int y = frame[1];
            int material = frame[2];
            int op = frame[3];
            
            if (material == 0) { // 기둥 
                if (op == 1) { // 설치
                    if (canBuildPillar(y, x)) {
                        pillars[y][x] = true;
                    }
                } else { // 삭제 
                    pillars[y][x] = false; 
                    if (!isValid()) { 
                        pillars[y][x] = true; // 유효하지 않으면 원상복구
                    }
                }
            } else { // 보 
                if (op == 1) {
                    if (canBuildBeam(y, x)) {
                        beams[y][x] = true;
                    }
                } else {
                    beams[y][x] = false; 
                    if (!isValid()) { 
                        beams[y][x] = true; // 유효하지 않으면 원상복구
                    }
                }
            }
        }
        
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (pillars[i][j]) result.add(new int[] {j, i, 0});
                if (beams[i][j]) result.add(new int[] {j, i, 1});
            }
        }
    
        result.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0]; 
            if (a[1] != b[1]) return a[1] - b[1]; 
            return a[2] - a[2];
        });
        
        int resSize = result.size();
        int[][] answer = new int[resSize][3];
        for (int i = 0; i < resSize; i++) {
            answer[i][0] = result.get(i)[0];
            answer[i][1] = result.get(i)[1];
            answer[i][2] = result.get(i)[2];
        }
        
        return answer;
    }
    
    private boolean isValid() {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (pillars[i][j] && !canBuildPillar(i, j)) return false; 
                if (beams[i][j] && !canBuildBeam(i, j)) return false; 
            }
        }
        
        return true;
    }
    
    
    private boolean canBuildPillar(int y, int x) {
        if (y == 0 || beams[y][x] || (x > 0 && beams[y][x - 1] || (y > 0 && pillars[y - 1][x]))) {
            return true;
        }
        return false; 
    }
    
    private boolean canBuildBeam(int y, int x) {
        if ((y > 0 && pillars[y - 1][x] || pillars[y - 1][x + 1])|| ((x > 0 && beams[y][x - 1]) && (x + 1 <= n && beams[y][x + 1]))) {
            return true;
        }
        return false; 
    }
}