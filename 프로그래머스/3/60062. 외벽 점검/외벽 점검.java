class Solution {
   	int weakLen; 
    boolean[] visited;
    int[] extended; 
    
    int result = Integer.MAX_VALUE;
    boolean found = false; 
    public int solution(int n, int[] weak, int[] dist) {
   		weakLen = weak.length;

        extended = new int[weakLen * 2]; 
        for (int i = 0; i < weakLen; i++) {
			extended[i] = weak[i]; 
            extended[i + weakLen] = weak[i] + n; 
        }
        
        /*
        for (int num: extended) {
            System.out.print(num + " ");
        }
		*/
        
        for (int i = 1; i <= dist.length; i++) {
            visited = new boolean[dist.length];
            permute(0, new int[i], dist);
            if (found) return i;
        }
        
        return -1;
    }
    
    // 순열을 통한 가능한 점검가능한 친구들의 모든 순서
    // 순열 결과를 selected를 통해 check함수에 넘김 
    public void permute(int depth, int[] selected, int[] dist) {
        if (depth == selected.length) {
            check(selected);
            return;
        }
        
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                visited[i] = true; 
                selected[depth] = dist[i];
                permute(depth + 1, selected, dist);
                visited[i] = false;
            }
        }
    }
    public void check(int[] friends) {
        for (int start = 0; start < weakLen; start++) {
            int friendsIdx = 0; 
            int checkRange = extended[start] + friends[friendsIdx];
            
            boolean allCover = true; 
            for (int i = start; i < start + weakLen; i++) {
                if (checkRange >= extended[i]) continue; // 커버 가능한 범위는 다 무시 
                
                friendsIdx++; // 커버 불가능할 때 다음 친구 
                if (friendsIdx >= friends.length) { // 친구 수를 넘어서면 조합 불가능한 것으로 판단 
                    allCover = false; 
                    break; 
                } 
                checkRange = extended[i] + friends[friendsIdx]; // 범위 갱신 
            }
            
            if (allCover) {
                result = friends.length; 
                found = true; 
                return; 
            }
        }    
    }
}