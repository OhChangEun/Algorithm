import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        boolean[] arr = new boolean[21];
        while (M-- > 0) {
            String input = br.readLine();
            String parts[] = input.split(" ");

            String op = parts[0];
            int value = 0;

            if (!op.equals("all") && !op.equals("empty")) {
                value = Integer.parseInt(parts[1]);
            }

            if (op.equals("add")) {
                arr[value] = true;
            } else if (op.equals("remove")) {
                arr[value] = false;
            } else if (op.equals("check")) {
                sb.append(arr[value] ? 1: 0);
                sb.append("\n");
            } else if (op.equals("toggle")) {
                arr[value] = !arr[value];
            } else if (op.equals("all")) {
                for (int i=0; i<21; i++) {
                    arr[i] = true;
                }
            } else if (op.equals("empty")) {
                for (int i=0; i<21; i++) {
                    arr[i] = false;
                }
            }
        }
        System.out.println(sb.toString());
    }
}
