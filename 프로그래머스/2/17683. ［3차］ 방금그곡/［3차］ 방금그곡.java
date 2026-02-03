import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
   
    	int maxRunTime = 0; 
        String result = null; 
        String[] mParts = convert(m);
        
        for (String music: musicinfos) {
            String[] parts = music.split(",");
            
            int startTime = toMinute(parts[0]);
            int endTime = toMinute(parts[1]);
            String musicName = parts[2];
            String[] musicInfo = convert(parts[3]);
            
            int runTime = endTime - startTime; // 재생 시간 
            
            int count = runTime / musicInfo.length;
          	int mod = runTime % musicInfo.length; 
            
            // 반복되는 부분
           	List<String> musicList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                for (String info: musicInfo) {
                    musicList.add(info);
                }
            }
            // 남은 부분 
            for (int i = 0; i < mod; i++) {
                musicList.add(musicInfo[i]);
            }
            
            // musicList안에 찾고자 하는 m이 포함되어있으면
            // 최대값 갱신
            if (compare(musicList.toArray(new String[0]), mParts)) {
                if (maxRunTime < runTime) {
                    maxRunTime = runTime; 
                    result = musicName;
                }
            }
        }
    
        return result == null ? "(None)" : result;
    }
    
    private boolean compare(String[] a, String[] b) {
        int aLen = a.length; 
        int bLen = b.length; 
        // 각 원소를 비교해서 연속으로 같으면 true 반환 
       
        for (int i = 0; i <= aLen - bLen; i++) {
        	boolean isEqual = true; 
            for (int j = 0; j < bLen; j++) {
                if (!b[j].equals(a[i + j])) {
                    isEqual = false;
                    continue;
                }
            }
                                                                  
            if (isEqual) {
                return true; 
            }
        }
        
        return false; 
    }
    
    private String[] convert(String str) {
        char[] arr = str.toCharArray();
        int len = arr.length;
		List<String> result = new ArrayList<>();
        
        for (int i = 0; i < len; i++) {
            if (i != len - 1 && arr[i + 1] == '#') { // 마지막 요소가 아닌 모든 요소에 대해 #검사
                result.add(arr[i] + "#");
                i++;
            } else {
                result.add(String.valueOf(arr[i]));
            }
        }
        
        return result.toArray(new String[0]);
    }
    
    private int toMinute(String time) {
       	String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        
        return hour * 60 + minute;
    }
}