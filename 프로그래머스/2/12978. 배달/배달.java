import java.util.*;

class Solution {
    final int INF = 1_000_000_000;
    public int solution(int N, int[][] road, int K) {
        int[][] distance = new int[N+1][N+1];
       	for (int i=1; i<=N; i++) {
            Arrays.fill(distance[i], INF);
            distance[i][i] = 0;
        } 
        
        for(int[] r: road) {
            int start = r[0];
            int end = r[1];
            int weight = r[2];
            
            // 두 마을 간 같은 도로 고려(기존 INF 값, 도로간 간격 )
            distance[start][end] = Math.min(distance[start][end], weight);
            distance[end][start] = Math.min(distance[end][start], weight);
        }
       
        for (int k=1; k<=N; k++) {
            for (int i=1; i<=N; i++) {
                for (int j=1; j<=N; j++) {
                    if (distance[i][k] < INF && distance[k][j] < INF) {
                        distance[i][j] = Math.min(distance[i][k] + distance[k][j], distance[i][j]);
                    }
                }
            }
        }

        int answer = 0;
        for (int i=1; i<=N; i++) {
        	if (distance[1][i] <= K) { // K이하의 배달 가능한 마을 개수 
               	answer++; 
            }
        }
        
        /*
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                System.out.print(distance[i][j] + " ");
            }
            System.out.println();
        }
      	*/
        
        return answer;
    }
}