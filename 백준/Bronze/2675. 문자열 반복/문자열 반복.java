import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i=0; i<T; i++) {
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);

            int R = Integer.parseInt(st.nextToken());
            String S = st.nextToken();

            for (int k=0; k<S.length(); k++) {
                for (int j=0; j<R; j++) {
                    sb.append(S.charAt(k));
                }
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
