import java.util.*;

class Solution {
    class File implements Comparable<File> {
        int idx; 
        String name; 
        String head; 
        int number; 
        
        public File(int idx, String name, String head, int number) {
            this.idx = idx; 
            this.name = name; 
            this.head = head; 
            this.number = number; 
        }
        
        @Override 
        public int compareTo(File other) {
            // head를 기준으로 비교 
            int headCmp = this.head.compareTo(other.head);
            if (headCmp != 0) return headCmp;
            if (this.number != other.number) return this.number - other.number; 
            return this.idx - other.idx;
        }
    }
    public String[] solution(String[] files) {
        
        PriorityQueue<File> pq = new PriorityQueue<>();
        for (int i = 0; i < files.length; i++) {
            File file = parseFileName(i, files[i]);
            pq.offer(file);
        }
        
        int idx = 0; 
        String[] result = new String[pq.size()];
        while (!pq.isEmpty()) {
            File file = pq.poll(); 
            result[idx++] = file.name; 
        }
        return result;
    }
    
    private File parseFileName(int idx, String file) {
        boolean isHead = true; 
        
        String head = "";
        int number = -1;
        StringBuilder sb = new StringBuilder();         
        StringBuilder numSb = new StringBuilder(); 
        for (int i = 0; i < file.length(); i++) {
            char ch = file.charAt(i);
            if (isHead && !Character.isDigit(ch)) { // 문자인 애들만 추가 
                sb.append(ch);
                continue; 
            } 
            
            head = sb.toString().toLowerCase();
            isHead = false; 
            if (Character.isDigit(ch) && numSb.length() < 6) { // 새로운 문자가 나오기 전이면서 숫자의 길이가 5자리일때만
                numSb.append(ch);
                continue; 
            }      
            break;
        }
        
        number = Integer.parseInt(numSb.toString());
        
        // System.out.println(idx + ": " + head + ", " + number);
        return new File(idx, file, head, number);
    }
    
}