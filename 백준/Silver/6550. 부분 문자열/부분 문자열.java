import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            String s = parts[0];
            String t = parts[1];

            int sLen = s.length();
            int tLen = t.length();

            int left = 0;
            int right = 0;
            int count = 0;

            while (left < sLen && right < tLen) {
                if (s.charAt(left) == t.charAt(right)) {
                    count++;
                    left++;
                    right++;
                } else {
                    right++;
                }
            }

            if (count == sLen) {
                sb.append("Yes").append("\n");
            } else {
                sb.append("No").append("\n");
            }
        }

        System.out.println(sb.toString());

    }
}