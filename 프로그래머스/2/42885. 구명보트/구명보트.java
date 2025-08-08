import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        // 몸무게 오름차순 정렬
       	Arrays.sort(people);
       
        int count = 0;
        int left = 0; 
        int right = people.length-1;
        
        while (left <= right) {
            if (people[left]+people[right] <= limit) {
                left++;
                right--;
            } else {
                right--;
            }
            count++;
        }
        
        return count;
    }
}