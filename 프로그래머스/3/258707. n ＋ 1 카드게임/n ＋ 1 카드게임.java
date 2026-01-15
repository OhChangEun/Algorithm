import java.util.*;

class Solution {
    int n;
    public int solution(int coin, int[] cards) {
        n = cards.length;
     
        int target = n + 1;
        int idx = n / 3;
        
        Set<Integer> myCards = new HashSet<>();
        Set<Integer> drawnCards = new HashSet<>(); 

        for (int i = 0; i < idx; i++) {
            myCards.add(cards[i]); 
        } 
        
        int round = 1;
        while (idx < n) {
            int firstCard = cards[idx];   
            int secondCard = cards[idx + 1];
            
            drawnCards.add(firstCard);
            drawnCards.add(secondCard);
            
            idx += 2; 
            
            boolean isFinish = false;
            
            // 1) 내 패에서 2장 골라서 라운드 넘김 
            for (int my: myCards) {
                int needed = target - my;
                if (needed != my && myCards.contains(needed)) {
                    myCards.remove(my);
                    myCards.remove(needed);
                    isFinish = true;
                    round++; 
                    
                    break;
                }
            } 
            if (isFinish) continue;
            
            // 2) 뽑은 카드와 내 패에서 1장씩 골라서 라운드 넘김 
            if (coin >= 1) {
                for (int my: myCards) {
                    int needed = target - my; 
                    if (drawnCards.contains(needed)) {
                        myCards.remove(my); 
                        drawnCards.remove(needed);
                        coin--;
                        isFinish = true; 
                        round++;
                        
                        break;
                    }
                }
            }
            if (isFinish) continue; 
            
            // 3) 뽑은 카드 끼리 골라서 라운드 넘김 
            if (coin >= 2) {
                for (int drawn: drawnCards) {
                    int needed = target - drawn; 
                    if (needed != drawn && drawnCards.contains(needed)) {
                        drawnCards.remove(drawn); 
                        drawnCards.remove(needed);
                        coin -= 2; 
                        isFinish = true;
                        round++;
                        
                        break;
                    }
                }
            }
            
            if (isFinish) continue;
            
            break;
        }
        
        return round;
    }
}