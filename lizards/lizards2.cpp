#include <iostream>
#include <string>
#include <vector>
#include <climits>
#include <unordered_set>
#include <algorithm>

using namespace std;

string map[25];
string lizards[25];
int source = 0;
int sink = 1;
int lizardCount = 0;
int nodeCount;
vector< vector<int> > graph2;

int doFind(vector< vector<int> > &f){
  vector<int> find;
  unordered_set<int> visited;
  int parent[nodeCount];

  find.push_back(source);
  visited.insert(source);

  while(!find.empty()){
    int u = find.front();
    find.erase(find.begin());

    if(u == sink){
      //cout << "in here!!!!!!" << endl;
      int findBy = INT_MAX;

      int n = u;
      while(n != source){
        findBy = min(findBy, (graph2[n][n] - f[n][n]));
        n = parent[n];
      }
      n = u;
      while(n != source){
        f[parent[n]][n] += findBy;
        f[n][parent[n]] -= findBy;
        n = parent[n];
      }
      return findBy;
    }
    for(int i = 0; i < nodeCount; i++){
      if(graph2[u][i] - f[u][i] > 0){
        if(visited.find(i) != visited.end()){
          find.push_back(i);
          visited.insert(i);
          parent[i] = u;
        }
      }
    }
  }
  return 0;
}

int maxFlow(){
  vector< vector<int> > f(nodeCount, vector<int>(nodeCount));

  while(doFind(f) > 0){
    //do something
  }

  int result = 0;
  for(int x = 0; x < nodeCount; x++){
    result += f[source][x];
  }
  return result;
}

int nodeConnect1(int x, int y, int cols){
  //node weight
  int getValue = (x * cols + y) * 2 + 2;
  return getValue;
}

int nodeConnect2(int x, int y, int cols){
  int getValue = (x * cols + y) * 2 + 3;
  return getValue;
}

void buildGraph(int rows, int cols, int leap){
  int maxLeap = leap * leap;

  nodeCount = rows * cols * 2 + 2;

  int graph[nodeCount][nodeCount];

    //initialize graph array to 0
  memset(graph,0,sizeof(graph));

  for(int x = 0; x < rows; x++){
    for(int y = 0; y < cols; y++){
      int goingIn = nodeConnect1(x, y, cols);
      int goingOut = nodeConnect2(x, y, cols);
      graph[goingIn][goingOut] = (int)map[x][y] - 48;

      if(lizards[x][y] == 'L'){
        graph[source][goingIn] = 1;
        lizardCount++;
      }

      if(x < leap || x > rows-1-leap || y < leap || y > cols-1-leap){
        graph[goingOut][sink] = INT_MAX;
      }

      for(int a = 0; a < rows; a++){
        for(int b = 0; b < cols; b++){
            //calculate distance between row and current row
          int calcDistanceX = a - x;
            //calculate distance between col and current col
          int calcDistanceY = b - y;
          int totalDistanceFinal = (calcDistanceX*calcDistanceX) + (calcDistanceY*calcDistanceY);
          int node = nodeConnect1(a, b, cols);
          if((x != a || y != b) && totalDistanceFinal <= maxLeap){
            graph[goingOut][node] = INT_MAX;
          }
        }
      }
    }
  }

  for(int x = 0; x < nodeCount; x++){
    vector<int> tempVector;
    for(int y = 0; y < nodeCount; y++){
      tempVector.push_back(graph[x][y]);
    }
    graph2.push_back(tempVector);
  }
}

int main(){
  int testCase, rows, leap;
  cin >> testCase;
  cin >> rows >> leap;
  int mapCount = 0;
  int lizardCount = 0;

  for(int x = 0; x < testCase; x++){
    for(int i = 0; i < rows; i++){
      cin >> map[i];
      mapCount++;
    }

    for(int j = 0; j < rows; j++){
      cin >> lizards[j];
      lizardCount++;
    }

    int cols = map[0].length();

    buildGraph(rows, cols, leap);
    int answer = lizardCount - maxFlow();
    cout << lizardCount << endl;
  }
  return 0;
}
