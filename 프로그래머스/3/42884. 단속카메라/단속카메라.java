import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        // 고속도로 나간 지점 기준 오름차순
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        
        int count = 0;
        int lastCameraPos = -30001;
        for (int i=0; i<routes.length; i++) {
            int inPos = routes[i][0]; // 고속도로 진입 지점
            int outPos = routes[i][1]; // 고속도로 진출 지점
            
            // 고속 도로 진입 지점이 최근 카메라 위치보다 뒤라면,
            // 카메라 설치 
            if (inPos > lastCameraPos) {
               	count++;
                lastCameraPos = outPos;
            }
        } 
       	/* 
        for (int i=0; i<routes.length; i++) {
        	for (int j=0; j<routes[i].length; j++) {
                System.out.print(routes[i][j] + " ");
            }    
            System.out.println();
        }
       	*/ 
        return count;
    }
}