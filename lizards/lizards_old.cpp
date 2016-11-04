#include <iostream>
#include <string>
#include <vector>
#include <queue>
#include <climits>

using namespace std;

#define N 505
char PilarMap[25][25];
char LizardMap[25][25];
long long g[N][N],
					rg[N][N],
					f[N],
					max_flow, path_flow;
int p[N];
int n, d, m, u, v, w, ans = 0;

struct edge{
	int u, v, w;
	edge(int _u, int _v, int _w): u(_u), v(_v), w(_w){}
};

// find path from source to sink.
long long bfs(int s, int t){

	//cout << s << " " << t << endl;

    // we have not visited any nodes except source  yet
    bool visited[n];
    memset(visited,false,sizeof(visited));
    visited[s] = true;

    // keep orderd list of nodes seen ... do far, only source
    queue <int> q;
    q.push(s);

    p[s] = -1; // source has no parent

    f[s] = INT_MAX; // flow TO source is max available (infinity)

    // as long as there are still nodes to "grow from"
    while (!q.empty()){

        // pick out next node (u) to grow from
        int u = q.front();
	q.pop();

        // which nodes can we get to? test each node ...
	for (int v=0; v<n; v++){

	    // make sure we have not already visited and more flow possible
            if (visited[v]==false && rg[u][v] > 0){
	        // can get from node u to node v ... v is a new growable node
                q.push(v);
                p[v] = u;  // got to v from u
                visited[v] = true; // now reached v

		// flow to v is min(flow to u, resid from u to v)
                f[v] = min(f[u],rg[u][v]);
            }
        }
    }

    if (visited[t]){
			return f[t];
		}
    else
      return 0;
}


int maxFlow(int s, int t){
    max_flow = 0;
    path_flow = bfs(s,t);
		cout << path_flow << endl;
    while (path_flow >0){
        for (v=t; v != s; v=p[v]){
            u = p[v];
            rg[u][v] -= path_flow;
            rg[v][u] += path_flow;
        }
        max_flow += path_flow;
	      path_flow = bfs(s,t);
    }

    	//build list of edges with flow
    vector<edge> used_edges;

			//build the edges for the pilar map layout
    for (int i=0;i<n;i++){
			for (int j=0;j<m;j++){
					// if there is an edge and we used it at all ...
				if (PilarMap[i][j] != '0'){
					used_edges.push_back(edge(i, j, PilarMap[i][j] - '0'));
				}
			}
		}

			//build the edges for the lizard layout map
		for(int x = 0; x < n; x++){
			for(int y = 0; y < m; y++){
				if(LizardMap[x][y] == 'L'){
					int x = (x * m + y) * 2;
					used_edges.push_back(edge(s, x, 1));
				}
			}
		}

		max_flow = used_edges[1].u;

		return max_flow;
}

int main(){
  int testCases;
	int count = 1;
  string line;

		//get number of test cases
  cin >> testCases;

		//go through test cases
  for(int x = 0; x < testCases; x++){
			//get number of rows in map
    cin >> n >> d;

			//read in the values for pilar map
    for(int i = 0; i < n; i++){
      cin >> PilarMap[i];
    }

			//read in values for lizard placement in map
    for(int j = 0; j < n; j++){
      cin >> LizardMap[j];
    }

      //get m for size n x m grid
    m = strlen(PilarMap[0]);

			//get source for maxflow
    int source = m * n * 2;

			//get sink for maxflow
    int sink = source + 1;

			//calculate maximum flow
		int answer = maxFlow(source, sink);

		if(answer == 0){
			cout << "Case #" << count << ": no lizards were left behind." << endl;
		}
		else{
			cout << "Case #" << count << ": " << answer << " lizards were left behind." << endl;
		}

		count++;
  }

  return 0;
}
