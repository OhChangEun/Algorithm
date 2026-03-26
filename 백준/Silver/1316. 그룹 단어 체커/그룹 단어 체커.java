import java.io.*;
import java.util.*;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int validCnt = 0;
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            if (check(input))
                validCnt++;
        }

        System.out.println(validCnt);
    }

    static boolean check(String input) {
        boolean[] used = new boolean[26];

        char prev = input.charAt(0);
        for (int i = 1; i < input.length(); i++) {
            char curr = input.charAt(i);

            if (used[curr - 'a']) {
                return false;
            }

            if (prev != curr) {
                used[prev - 'a'] = true;
                prev = curr;
            }
        }

        return true;
    }
}