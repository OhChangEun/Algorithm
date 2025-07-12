import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String S = br.readLine();

        int pattern = 0;
        int count = 0;
        char[] arr = S.toCharArray();
        for (int i=1; i<M-1; i++) {
            if (arr[i-1] == 'I' && arr[i] == 'O' && arr[i+1] == 'I') {
                pattern++;

                if (pattern == N) {
                    pattern--;
                    count++;
                }
                i++;
            } else {
                pattern = 0;
            }
        }
        System.out.println(count);
    }
}