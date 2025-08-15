import java.util.*;

class Solution {
    public List<String> getValidString(String str) {
       	List<String> result = new ArrayList<>();	
        str = str.toLowerCase();
       	 
        for (int i=1; i<str.length(); i++) {
           	char ch1 = str.charAt(i-1); 
           	char ch2 = str.charAt(i); 
            
            if (Character.isLetter(ch1) && Character.isLetter(ch2)) {
                result.add("" + ch1 + ch2);
            }
        }
        return result;
    }
    public int solution(String str1, String str2) {
        final int NUM = 65536;
        
        List<String> list1 = getValidString(str1);
        List<String> list2 = getValidString(str2);
      
        List<String> intersection = new ArrayList<>();
       	List<String> tempList2 = new ArrayList<>(list2); 
        for (String word: list1) {
            if (tempList2.contains(word)) {
                intersection.add(word);
                tempList2.remove(word); // 중복제거
            }
        }
        
      	// 합집합의 길이 = list1 + list2 - 교집합의 길이   
        int unionSize = list1.size() + list2.size() - intersection.size();
        
        if (unionSize == 0) 
            return NUM;  
       	/* 
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(intersection);
       	*/ 
        
        return (int)((double)intersection.size() / unionSize * NUM);
    }
}