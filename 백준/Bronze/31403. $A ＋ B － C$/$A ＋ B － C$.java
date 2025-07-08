import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int C = Integer.parseInt(br.readLine());

        int res = A + B - C;
        System.out.println(res);

        String strA = Integer.toString(A);
        String strB = Integer.toString(B);

        int strRes = Integer.parseInt(strA+strB) - C;
        System.out.println(strRes);
    }
}