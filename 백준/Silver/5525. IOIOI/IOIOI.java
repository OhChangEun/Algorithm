import java.io.*;
import java.util.*;

public class Main {

    public static String getPn(int n) {
        String res = "";
        int size = 3 + 2*(n-1);
        for (int i=0; i<size; i++) {
            if (i%2 == 0) {
                res += "I";
            } else {
                res += "O";
            }
        }
        return res;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String S = br.readLine();

        String Pn = getPn(N);
        // System.out.println(Pn);

        int res = 0;
        int maxIndex = M - Pn.length() + 1;
        for (int i=0; i < maxIndex; i++) {
            int left = i;
            boolean isContain = true;
            for (int j=0; j<Pn.length(); j++) {
                if (S.charAt(left) != Pn.charAt(j)) {
                     isContain = false;
                     break;
                }
                left++;
            }

            if (isContain) {
                res++;
            }
        }
        System.out.println(res);
    }
}