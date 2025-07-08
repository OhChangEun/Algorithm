import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        String op;
        if (A > B) {
            op = ">";
        } else if (A < B) {
            op = "<";
        } else {
            op = "==";
        }

        System.out.println(op);
    }
}