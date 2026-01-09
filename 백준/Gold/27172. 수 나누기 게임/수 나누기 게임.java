import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int MAX = 1000000;

    static int n;
    static int[] cards;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        cards = new int[n];

        String[] parts = br.readLine().split(" ");
        boolean[] exists = new boolean[MAX + 1];
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(parts[i]);
            cards[i] = num;
            exists[num] = true;
        }

        int[] scores = new int[MAX + 1];
        for (int card: cards) {
            for (int i = card * 2; i <= MAX; i += card) {
                if (exists[i]) {
                    scores[card] += 1;
                    scores[i] -= 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int card = cards[i];
            sb.append(scores[card]).append(" ");
        }
        System.out.println(sb.toString());
    }
}