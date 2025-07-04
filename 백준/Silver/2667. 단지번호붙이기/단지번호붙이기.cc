#include <iostream>
#include <vector>
#include <algorithm>

using namespace std; 

int N; 
vector<vector<int>> field;
vector<vector<bool>> visited;
vector<int> group;

int dx[4] = { 0, 0, -1, 1 };
int dy[4] = { 1, -1, 0, 0 };

int dfs(int x, int y) {
  visited[y][x] = true;
  int size = 1;
  
  for (int dir=0; dir<4; dir++) {
    int nx = x + dx[dir];
    int ny = y + dy[dir];

    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
      if (field[ny][nx] == 1 && !visited[ny][nx]) {
        size += dfs(nx, ny);
      }
    }
  }
  return size;
}

int main() {

  cin >> N; 

  field = vector<vector<int>>(N, vector<int>(N, 0));
  visited = vector<vector<bool>>(N, vector<bool>(N, false));
  
  for (int i=0; i<N; i++) {
    string input;
    cin >> input; 
    for (int j=0; j<N; j++) {
      field[i][j] = input[j] - '0';
    }
  }
  int count = 0 ;
  for (int y=0; y<N; y++) {
    for (int x=0; x<N; x++) {
      if (field[y][x] == 1 && !visited[y][x]) {
        int size = dfs(x, y);
        group.push_back(size);
        count++;
      }
    }
  }

  sort(group.begin(), group.end());
  
  cout << count << '\n';
  for (int size: group) {
    cout << size << '\n';
  }  

  return 0;
}