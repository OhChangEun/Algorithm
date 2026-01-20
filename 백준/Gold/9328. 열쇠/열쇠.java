import java.util.*;
import java.io.*;

public class Main {
    static int answer;
    static int row, col;
    static char[][] map;

    static class Point {
        int y, x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static Set<Character> keys;
    static Map<Character, List<Point>> doors;
    static boolean[][] visited;

    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] parts = br.readLine().split(" ");
            row = Integer.parseInt(parts[0]);
            col = Integer.parseInt(parts[1]);

            map = new char[row + 2][col + 2];

            for (int i = 0; i <= row + 1; i++) {
                Arrays.fill(map[i], '.');
            }
            for (int i = 1; i <= row; i++) {
                String input = br.readLine();
                for (int j = 1; j <= col; j++) {
                    char ch = input.charAt(j - 1);
                    map[i][j] = ch;
                }
            }

            //print();

            keys = new HashSet<>();
            String inputKeys = br.readLine();
            if (!inputKeys.equals("0")) {
                for (char key: inputKeys.toCharArray()) {
                    keys.add(key);
                }
            }

            visited = new boolean[row + 2][col + 2];
            doors = new HashMap<>();
            answer = 0;
            bfs();

            sb.append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void bfs() {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            int cy = curr.y;
            int cx = curr.x;

            for (int dir = 0; dir < 4; dir++) {
                int ny = cy + dy[dir];
                int nx = cx + dx[dir];

                if (ny < 0 || nx < 0 || ny >= row + 2 || nx >= col + 2) continue;
                if (map[ny][nx] == '*' || visited[ny][nx]) continue;

                visited[ny][nx] = true;

                char cell = map[ny][nx];
                if (cell == '$') {
                    answer++;
                    queue.offer(new Point(ny, nx));
                } else if (Character.isLowerCase(cell)) {
                    keys.add(cell);
                    queue.offer(new Point(ny, nx));

                    // 새로 들어온 키 중에 문을 열 수 있다면
                    if (doors.containsKey(cell)) {
                        // 문에 관련된 좌표에 대해 queue에 넣고
                        for (Point door: doors.get(cell)) {
                            queue.offer(door);
                        }
                        doors.remove(cell); // 열 수 있는 좌표 삭제
                    }
                } else if (Character.isUpperCase(cell)) {
                    char key = Character.toLowerCase(cell);

                    // 키가 있다면
                    if (keys.contains(key)) {
                        queue.offer(new Point(ny, nx));
                    } else { // 키가 없다면
                        doors.putIfAbsent(key, new ArrayList<>());
                        doors.get(key).add(new Point(ny, nx));
                        visited[ny][nx] = false;
                    }
                } else {
                    queue.offer(new Point(ny, nx));
                }
            }
        }
    }
}
