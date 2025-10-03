import java.util.*;

class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int w = weak.length;
   	
        // [1, 5, 6, 10, 13, 17, 18, 22]
        int[] extended = new int[w * 2];
        for (int i=0; i<w; i++) {
            extended[i] = weak[i];
            extended[i + w] = weak[i] + n;
        }
        
        // 친구들의 모든 순열 
        List<int[]> perms = new ArrayList<>();
        boolean[] used = new boolean[dist.length];
        permute(0, perms, dist, new int[dist.length], used); // 깊이, 결과, 친구 배열, 현재 배열, 방문 여부
        
        int result = Integer.MAX_VALUE;
        
        // 모든 취약점에 대해 
        // 모든 친구들을 대입해서 
        // 최소 인원을 구한다. 
     	for (int start = 0; start < w; start++) {
           	for (int[] perm: perms) {
             	int friendIdx = 0;
                int coverRange = extended[start] + perm[friendIdx];
              
                boolean isSuccess = true;
                for (int idx = start; idx < start + w; idx++) {
            		if (extended[idx] > coverRange) { // 현재 친구가 커버해야할 범위를 벗어난 경우 
                        friendIdx++; // 다음 친구 
                        if (friendIdx >= dist.length) {
                			isSuccess = false; 
                            break;
                        }
                        coverRange = extended[idx] + perm[friendIdx];
                    }
                }
                if (isSuccess) {
                    result = Math.min(result, friendIdx + 1);
                }
            } 
        } 
        /*
        for (int num: extended) {
            System.out.print(num + " ");
        }
        for (int[] perm: perms) {
           	for (int num: perm) {
       			System.out.print(num + " ");
            } 
            System.out.println();
        }
        */
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    
    private void permute(int depth, List<int[]> perms, int[] dist, int[] curr, boolean[] used) {
        if (depth == dist.length) {
           	perms.add(curr.clone()); 
            return; 
        }
        
        for (int i=0; i<dist.length; i++) {
            if (!used[i]) {
                used[i] = true;
               	curr[depth] = dist[i];
               	permute(depth + 1, perms, dist, curr, used); 
                used[i] = false;
            }
        }
    }
}