#include <iostream>
#include <string>
#include <vector>
#include <climits>
#include <unordered_set>
#include <algorithm>
#include <fstream>

using namespace std;

string map[25];
string lizards[25];
int lizardCount = 0;

class Lizard{
  public:
    Lizard();
    int searchBFS(vector< vector <int> > &);
    void readMap();
    void readLizardMap();
    int maxFlow();
    int nodeConnect1(int, int, int);
    int nodeConnect2(int, int, int);
    void buildGraph(int, int, int);

  private:
    int source;
    int sink;
    int nodeCount;
    vector< vector<int> > graph2;
};

  //intialize source and sink
Lizard::Lizard(){
  source = 0;
  sink = 1;
}

  //find path from source to sink
int Lizard::searchBFS(vector< vector<int> > &f){
  vector<int> find;
  unordered_set<int> visited;
  int parent[nodeCount];

    //add calculated source
  find.push_back(source);

    //add source to first visited
  visited.insert(source);

    //initialize parent array to -1
  memset(parent, -1, sizeof(parent));

  while(!find.empty()){
    //cout << "notEmpty" << endl;
      //get first value and store in temp
    int u = find.front();
    //cout << u << endl;
    //cout << sink << "THIS IS SINK" << endl;
      //get rid of first value in "queue" for next value
    find.erase(find.begin());

    if(u == sink){
        //cout << "in here!!!!!!" << endl;
      int findBy = INT_MAX;

      int n = u;
      while(n != source){
          //compare values to infinity and see which contains 0
        findBy = min(findBy, (graph2[parent[n]][n] - f[parent[n]][n]));
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
      //cout << graph2[u][i] - f[u][i] << endl;
      if(graph2[u][i] - f[u][i] > 0){
        const bool is_in = visited.find(i) != visited.end();

        if(!is_in){
          find.push_back(i);
          visited.insert(i);
          parent[i] = u;
        }
      }
    }
  }
  return 0;
}

  //get maximum flow of lizards
int Lizard::maxFlow(){

  int flow[nodeCount][nodeCount];

    //initialize flow to 0
  memset(flow, 0, sizeof(flow));

    //2d vector for graph
  vector< vector<int> > f(0);

    //store flow to nodes in 2d vector
  for(int x = 0; x < nodeCount; x++){
    vector<int> tempVector;
    for(int y = 0; y < nodeCount; y++){
      tempVector.push_back(flow[x][y]);
    }
    f.push_back(tempVector);
  }

  /*for(int x = 0; x < nodeCount; x++){
    for(int y = 0; y < nodeCount; y++){
      cout << graph2[x][y];
    }
    cout << endl;
  }*/

  while(searchBFS(f) > 0){
    //do something
    //cout << "hello" << endl;
  }

  /*for(int x = 0; x < nodeCount; x++){
    for(int y = 0; y < nodeCount; y++){
      cout << f[x][y];
    }
    cout << endl;
  }*/

  int result = 0;

    //add values from flow nodes into the final result
  for(int x = 0; x < nodeCount; x++){
    result += f[source][x];
  }
  return result;
}

  //node goint in
int Lizard::nodeConnect1(int x, int y, int cols){
    //node capacity going in
  int getValue = (x * cols + y) * 2 + 2;
  return getValue;
}

int Lizard::nodeConnect2(int x, int y, int cols){
    //node capacity going out
  int getValue = (x * cols + y) * 2 + 3;
  return getValue;
}

void Lizard::buildGraph(int rows, int cols, int leap){
  int maxLeap = leap * leap;

  nodeCount = rows * cols * 2 + 2;

  cout << nodeCount << endl;

  int graph[nodeCount][nodeCount];

    //initialize graph array to 0
  memset(graph,0,sizeof(graph));

  for(int x = 0; x < rows; x++){
    for(int y = 0; y < cols; y++){
        //going into node
      int goingIn = nodeConnect1(x, y, cols);

        //going out of node
      int goingOut = nodeConnect2(x, y, cols);

        //convert char from read in value into the graph integer matrix
      graph[goingIn][goingOut] = (int)map[x][y] - 48;

        //add a "going in" node if lizard is placed here
      if(lizards[x][y] == 'L'){
        graph[source][goingIn] = 1;
        lizardCount++;
      }

      if(x < leap || x > rows-1-leap || y < leap || y > cols-1-leap){
        graph[goingOut][sink] = INT_MAX;  //set to infinity
      }

      for(int a = 0; a < rows; a++){
        for(int b = 0; b < cols; b++){
            //calculate distance between row and current row
          int calcDistanceX = a - x;
            //calculate distance between col and current col
          int calcDistanceY = b - y;

            //get the distance between the points
          int totalDistanceFinal = (calcDistanceX*calcDistanceX) + (calcDistanceY*calcDistanceY);
          int node = nodeConnect1(a, b, cols);
          if((x != a || y != b) && totalDistanceFinal <= maxLeap){
            graph[goingOut][node] = INT_MAX;
          }
        }
      }
    }
  }

    //add the current graph to the global 2d vector for use by
    //searching function
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
  int caseNumber = 0;

    //read in amount of test cases
  cin >> testCase;

    //initialize total lizard count
  int mapCount = 0;

    //go through test cases
  for(int x = 0; x < testCase; x++){
    Lizard myLizard;

      //read in map rows and leap value
    cin >> rows >> leap;

    caseNumber++;

      //read in pilar map
    for(int i = 0; i < rows; i++){
      cin >> map[i];
      mapCount++;
    }

      //read in lizard placement map
    for(int j = 0; j < rows; j++){
      cin >> lizards[j];
    }

      //get the value "m" or the amount of columns
    int cols = map[0].length();

      //build the graph
    myLizard.buildGraph(rows, cols, leap);

      //get solution by taking the amount that can leave subtracted from total lizards
    int answer = lizardCount - myLizard.maxFlow();

      //print the results
    switch(answer){
      case 0: cout << "Case #" << caseNumber << ": no lizard was left behind." << endl;
        break;

          //only print "lizard" and not "lizards" if there is only 1
      case 1: cout << "Case #" << caseNumber << ": " << answer << " lizard was left behind." << endl;
        break;
      default: cout << "Case #" << caseNumber << ": " << answer << " lizards were left behind." << endl;
    }
      //reinitialize lizard count for next test case
    lizardCount = 0;
  }
  return 0;
}
