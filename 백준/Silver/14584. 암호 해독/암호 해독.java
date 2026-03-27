import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int n = Integer.parseInt(br.readLine());

        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        for (int i = 1; i <= 26; i++) {
            StringBuilder sb = new StringBuilder();

            for (char ch: input.toCharArray()) {
                sb.append((char)((ch + i) % 26 + 'a'));
            }

            for (String word: words) {
                if (sb.toString().contains(word)) {
                    System.out.println(sb.toString());
                    return;
                }
            }
        }
    }
}