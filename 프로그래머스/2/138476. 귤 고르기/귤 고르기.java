import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
       
        int size = tangerine.length;
      
        // 귤 크기에 따른 귤 개수를 담는 배열 
     	Integer[] arr = new Integer[1_000_0001];
       
        // 0으로 초기화
        Arrays.fill(arr, 0);
        
        // 귤 크기에 따라 귤 개수 담기 
        for (int i=0; i<size; i++) {
            arr[tangerine[i]]++;
        } 
        
        // 귤 개수에 따라 내림차순  
        Arrays.sort(arr, (a, b) -> b - a);
      
        // 상자에 담을 수 있는 귤 종류 
        int count = 0;
        for (int i=0; i<arr.length; i++) {
            k -= arr[i];
        	// System.out.println(arr[i]);
            if (k <= 0) {
                count = i+1;
                break;
            }
        }
        /* 
        int sizeCount = 0; 
        for (int i=0; i<arr.length; i++) {
       		if (arr[i] != 0) {
                sizeCount++;
            } 
        } 
        */
       
        // System.out.println(sizeCount);
        
        return count;
    }
}