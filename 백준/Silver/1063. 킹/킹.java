import java.util.*;
import java.io.*;

public class Main {

    public static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // A1 >> (7, 0)
    public static Point transitionPos(String word) {
        int col = word.charAt(0) - 'A'; // A
        int row = word.charAt(1) - '0'; // 1

        return new Point(7-row+1, col);
    }

    // (7, 0) >> A1
    public static String transitionStr(Point p) {
        char col = (char) ('A' + p.y);
        int row = 8 - p.x;

        return "" + col + row;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");
        String kingPos = parts[0];
        String stonePos = parts[1];
        int N = Integer.parseInt(parts[2]);

        Point king = transitionPos(kingPos);
        Point stone = transitionPos(stonePos);

        Map<String, Point> map = new HashMap<>();
        map.put("LT", new Point(-1, -1));
        map.put("T", new Point(-1, 0));
        map.put("RT", new Point(-1, 1));

        map.put("L", new Point(0, -1));
        map.put("R", new Point(0, 1));

        map.put("LB", new Point(1, -1));
        map.put("B", new Point(1, 0));
        map.put("RB", new Point(1, 1));

        // 이동 경로 n개 입력
        String[] lines = new String[N];
        int stoneNx = 0;
        int stoneNy = 0;
        for (int i = 0; i < N; i++) {
            //lines[i] = br.readLine();
            Point d = map.get(br.readLine());
            // System.out.println(d.x + " " + d.y);

            int kx = king.x + d.x;
            int ky = king.y + d.y;

            if (kx >= 0 && kx < 8 && ky >= 0 && ky < 8) {
                if (kx == stone.x && ky == stone.y) {
                    int sx = stone.x + d.x;
                    int sy = stone.y + d.y;

                    if (sx >= 0 && sx < 8  && sy >= 0 && sy < 8) {
                        king.x = kx;
                        king.y = ky;
                        stone.x = sx;
                        stone.y = sy;
                    }
                } else {
                    king.x = kx;
                    king.y = ky;
                }
            }
        }

        System.out.println(transitionStr(king));
        System.out.println(transitionStr(stone));

//        for (int i=0; i<N; i++) {
//            System.out.println(lines[i]);
//        }
    }
}