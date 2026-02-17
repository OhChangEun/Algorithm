import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int M;
    static int[][] map;
    static int answer = 0;
    static Shark[] sharks;

    static int UP = 1;
    static int DOWN = 2;
    static int RIGHT = 3;
    static int LEFT = 4;

    static class Shark {
        int speed;
        int dir;
        int size;

        public Shark(int speed, int dir, int size) {
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");
        R = Integer.parseInt(parts[0]);
        C = Integer.parseInt(parts[1]);
        M = Integer.parseInt(parts[2]);

        map = new int[R + 1][C + 1];
        sharks = new Shark[M + 1];

        for (int i = 0; i < M; i++) {
            parts = br.readLine().split(" ");
            int r = Integer.parseInt(parts[0]); // 행
            int c = Integer.parseInt(parts[1]); // 열
            int s = Integer.parseInt(parts[2]); // 스피드
            int d = Integer.parseInt(parts[3]); // 방향
            int z = Integer.parseInt(parts[4]); // 크기

            map[r][c] = i + 1; // map에는 인덱스만 저장(상어는 인덱스로 저장)
            sharks[i + 1] = new Shark(s, d, z); // 1-based
        }

        for (int col = 1; col <= C; col++) {
            // 물고기 잡기
            fishing(col);

            // 물고기 움직임
            move();
        }

        System.out.println(answer);
    }

    private static void move() {
        List<int[]> moveList = new ArrayList<>();

        for (int row = 1; row <= R; row++) {
            for (int col = 1; col <= C; col++) {
                int idx = map[row][col];
                if (idx != 0) { // 모든 상어에 대해 위치 이동
                    int speed = sharks[idx].speed;
                    int dir = sharks[idx].dir;
                    int size = sharks[idx].size;

                    // 새로운 위치로 이동
                    // speed만큼 움직일건데
                    // dir == 2, 3일 때, 좌우 일 때
                    // x 방향으로 나가지 않게끔 nx가 0이거나 r + 1이면 이전 걸로 위치
                    // 움직인 장소 값을 하나를 갱신하면 아직 갱신되지 않은 애들을 어떻게 할지 생각

                    if (dir == RIGHT || dir == LEFT) {
                        int nx = col;
                        int dx = (dir == RIGHT) ? 1 : -1;

                        for (int i = 0; i < speed; i++) {
                            nx += dx;

                            // 다시 내부로 이동
                            if (nx == C + 1) {
                                nx = C - 1;
                                dx *= -1; // 방향 전환
                            } else if (nx == 0) {
                                nx = 2;
                                dx *= -1; // 방향 전환
                            }
                        }
                        sharks[idx].dir = (dx == 1) ? RIGHT : LEFT;
                        moveList.add(new int[] {idx, row, nx});
                    } else if (dir == UP || dir == DOWN) {
                        int ny = row;
                        int dy = (dir == UP) ? -1 : 1;

                        for (int i = 0; i < speed; i++) {
                            ny += dy;

                            if (ny == R + 1) {
                                ny = R - 1;
                                dy = -dy;
                            } else if (ny == 0) {
                                ny = 2;
                                dy = -dy;
                            }
                        }
                        sharks[idx].dir = (dy == -1) ? UP : DOWN;
                        moveList.add(new int[] {idx, ny, col});
                    }

                    map[row][col] = 0;
                }
            }
        }

        // 이동한 위치로 다시 그리기
        for (int[] move: moveList) {
            int idx = move[0];
            int y = move[1];
            int x = move[2];

            if (map[y][x] == 0) {
                map[y][x] = idx;
            } else {
                int firstIdx = map[y][x];
                int firstSharkSize = sharks[firstIdx].size;
                int secondSharkSize = sharks[idx].size;

                if (firstSharkSize < secondSharkSize) {
                    map[y][x] = idx;
                }
            }
        }
    }

    // 현재 위치에서 땅과 가장 가까운 물고기 잡기
    private static void fishing(int currCol) {
        for (int row = 1; row <= R; row++) {
            int idx = map[row][currCol];
            if (idx != 0) {
                answer += sharks[idx].size;
                map[row][currCol] = 0;
                return;
            }
        }
    }
}
