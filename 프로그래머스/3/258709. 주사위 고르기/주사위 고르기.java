import java.util.*;

class Solution {
    int n;
    int[] selected;
    int[] result;
    int[][] dices;
    int maxWin = 0;
    
    public int[] solution(int[][] dice) {
        n = dice.length; 
        
        dices = dice; 
        selected = new int[n / 2];
        result = new int[n / 2];
        dfs(0, 0);
        
        return result; 
    }
    
    private int getWinResult(int[] selected) {
        boolean[] used = new boolean[n];
        for (int s: selected) used[s] = true; 
                
        int idx = 0;
        int[] otherSelected = new int[n / 2];     
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                otherSelected[idx] = i;
                idx++;
            }
        }
          
        List<Integer> sumA = getSumList(selected); 
        List<Integer> sumB = getSumList(otherSelected);
     
        Collections.sort(sumA);
        Collections.sort(sumB);
        
        int count = 0; 
        int right = 0;
        for (int left = 0; left < sumA.size(); left++) {
            while (right < sumB.size() && sumA.get(left) > sumB.get(right)) {
                right++;
            }
            count += right;    
        }
        
        return count;
    }
    
    private List<Integer> getSumList(int[] selected) {
        List<Integer> list = new ArrayList<>();
        
        sumDfs(selected, 0, 0, list);
        
        return list;
    }
    
    private void sumDfs(int[] selected, int depth, int sum, List<Integer> list) {
        if (depth == n / 2) {
            list.add(sum);
            return; 
        }
    
        int idx = selected[depth];
        for (int d: dices[idx]) {
            sumDfs(selected, depth + 1, sum + d, list); 
        }
    }
    
    private void dfs(int depth, int start) {
        if (depth == n / 2) {
            int win = getWinResult(selected); 
            if (maxWin < win) {
                maxWin = win; 
                for (int i = 0; i < n / 2; i++) {
                    result[i] = selected[i] + 1;
                }
            }
            
            return; 
        }
        
        for (int i = start; i < n; i++) {
            selected[depth] = i;
            dfs(depth + 1, i + 1);
        }
        
    }
}