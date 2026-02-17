import java.util.*;

class Solution {
    // <이모티콘 가격, 할인율>
    int[][] users;
    int[] emoticons; 
    int maxPlusUser = 0; 
    int maxCost= 0; 
    
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users; 
        this.emoticons = emoticons; 
        
        int[] selected = new int[emoticons.length];
        dfs(0, selected);
        
        return new int[] {maxPlusUser, maxCost};
    }

    private void dfs(int depth, int[] selected) {
        if (depth == selected.length) {
            
            int plusUserCnt = 0; // 플러스 서비스를 사용하는 유저
            int totalCost = 0; // 플러스 서비스를 사용하지 않는 유저들이 사용하는 총 매출액 
            for (int[] user: users) {
                int userSale = user[0]; // 유저가 생각하는 세일 기준 
                int userPrice = user[1]; // 유저가 생각하는 총 가격
                
                int cost = 0; // 각 유저가 이모티콘에 사용하는 비용
                for (int i = 0; i < selected.length; i++) {
                    if (userSale <= selected[i]) { // 유저가 생각하는 세일 기준보다 현재 세일하는게 더 크거나 같을 때 
                        cost += emoticons[i] * (100 - selected[i]) / 100; // 이모티콘에 할인율을 적용해서 계산 
                    }
                }
                
                if (userPrice <= cost) { // 유저가 생각하는 총 가격보다 비용이 비싸면 
                    plusUserCnt++; // 플러스 서비스 가입 
                } else { // 더 싸면 
                    totalCost += cost; // 이모티콘 매출액에 추가 
                }
                
                if (maxPlusUser < plusUserCnt) {
                    maxPlusUser = plusUserCnt; 
                    maxCost = totalCost; 
                } else if (maxPlusUser == plusUserCnt && maxCost < totalCost) {
                    maxCost = totalCost; 
                }
            }
                        
            return; 
        }
        
        for (int sale = 10; sale <= 40; sale += 10) {
            selected[depth] = sale; 
            dfs(depth + 1, selected);
        }
    }
}