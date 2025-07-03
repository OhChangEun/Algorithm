#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int MAX = 101;

vector<vector<int>> graph(MAX);

void bfs(vector<vector<int>>& result, int n) {
   for (int start=0; start<n; start++) {
      vector<bool> visited(n, false);
      queue<int> q;
      q.push(start);

      while (!q.empty()) {
         int curr = q.front();
         q.pop();

         for (int next=0; next<n; next++) {
            if (graph[curr][next] == 1 && !visited[next]) {
               q.push(next);
               visited[next] = true;
               result[start][next] = 1;
            }
         }
      }
   }
}

int main() {
   int N;
   cin >> N;

   for (int i=0; i<N; i++) {
      for (int j=0; j<N; j++) {
         int num;
         cin >> num;
         graph[i].push_back(num);
      }
   }

   vector<vector<int>> result(N,vector<int>(N, 0));
   bfs(result, N);

   for (int i=0; i<N; i++) {
      for (int j=0; j<N; j++) {
         cout << result[i][j] << " ";
      }
      cout << '\n';
   }

   return 0;
}
