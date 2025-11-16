import java.util.*;

class Solution {
    public int solution(int[][] routes) {
   		Arrays.sort(routes, (a, b) -> a[1] - b[1]);
      
        /*
       	for(int[] route: routes) {
        	System.out.println(route[0] + ", " + route[1]);
        } 
        */
       
        int cameraCnt = 0;
        int lastCameraPos = -30001;
        for (int[] route: routes) {
           	int start = route[0]; 
           	int end = route[1]; 
            if (lastCameraPos < start) {
               	cameraCnt++;
               	lastCameraPos = end; 
            }
        }
        
        return cameraCnt;
    }
}