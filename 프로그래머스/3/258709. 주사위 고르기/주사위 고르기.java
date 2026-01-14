import java.util.*;

class Solution {
   	int n;
    boolean[] visited; 
    int[] selected;
    int maxWin = 0;
    int[] result;
    
    public int[] solution(int[][] dice) {
   		n = dice.length;
        result = new int[n / 2];
        selected = new int[n / 2]; 
       	visited = new boolean[n];
        dfs(dice, 0, 0);
        
        return result; 
    }
   	
    private int getWinCount(int[][] dice, int[] selected) {
        boolean[] used = new boolean[n];
        for (int s: selected) used[s] = true; 
        
        int idx = 0;
        int[] otherSelected = new int[selected.length];
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
        		otherSelected[idx] = i;
               	idx++; 
            }
        }
        
        List<Integer> sumA = getSumList(dice, selected);
        List<Integer> sumB = getSumList(dice, otherSelected);
        
        Collections.sort(sumA);
        Collections.sort(sumB);
        
        // sumA와 sumB간 투 포인터를 활용해서 더 큰 A개수 구하는 로직 
       	int win = 0; 
        int right = 0;
       	for (int left = 0; left < sumA.size(); left++) {
           	while (right < sumB.size() && sumB.get(right) < sumA.get(left)) {
               	right++;
            }
            
            win += right; 
        }	 
        
        return win;
    }
    
    private List<Integer> getSumList(int[][] dice, int[] selected) {
        List<Integer> result = new ArrayList<>();
        
        sumDfs(dice, selected, 0, 0, result);
        
        return result;
    }
    
    private void sumDfs(int[][] dice, int[] selected, int depth, int sum, List<Integer> result) {
    	if (depth == selected.length) {
      		result.add(sum); 
            return;
        }
      
        int idx = selected[depth];
        for (int d: dice[idx]) {
        	sumDfs(dice, selected, depth + 1, sum + d, result);
        }
    }
    
    private void dfs(int[][] dice, int depth, int curr) {
        if (depth == n / 2) {
            int win = getWinCount(dice, selected);
            // a가 선택한 결과: selected
            if (maxWin < win) {
               	maxWin = win;
                for (int i = 0;  i < selected.length; i++) {
                	result[i] = selected[i] + 1;
                }
            }
            return;
        }
        
        for (int i = curr; i < n; i++) {
           	if (!visited[i]) {
               	visited[i] = true; 
                selected[depth] = i;
                dfs(dice, depth + 1, i + 1); 
               	visited[i] = false; 
            } 
        }
        
    }
}