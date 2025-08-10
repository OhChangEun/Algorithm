import java.util.*;

class Solution {
    public int sum(int[] arr, int start, int end) {
       	int sum = 0; 
        if (start <= end) {
            for (int i=start; i<=end; i++) {
                sum += arr[i];
            }
        } else {
            for (int i=start; i<arr.length; i++) {
                sum += arr[i];
            } 
            for (int i=0; i<=end; i++) {
                sum += arr[i];
            }
        }
        return sum;
    }
    public int solution(int[] elements) {
        int answer = 0;
       
        Set<Integer> set = new HashSet<>();
     
        int size = elements.length;
       	// System.out.println(sum(elements, 3, 1)); 
       
        for (int i=0; i<size; i++) { // 시작점
            for (int start=0; start<size; start++) { // 배열의 길이 
                int end = (start+i) % size;

                int res = sum(elements, start, end);
                set.add(res);
            }
        }
        
        return set.size();
    }
}