import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        
        int[] freq = new int[26];
        for (char ch: input.toLowerCase().toCharArray()) {
            freq[ch - 'a']++;
        }
        
        char result = '?';
        int maxFreq = 0;
        
        for (int i = 0; i < 26; i++) {
            if (maxFreq < freq[i]) {
                maxFreq = freq[i]; 
                result = (char)(i + 'A'); 
            } else if (maxFreq == freq[i]) {
                result = '?';
            }
        }
        
        System.out.println(result);   
    }
}