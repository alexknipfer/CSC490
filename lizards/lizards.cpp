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
int n, d, m, u, v, w;

struct edge{
	int u, v, w;
	edge(int _u, int _v, int _w): u(_u), v(_v), w(_w){}
};

// find path from source to sink.
long long bfs(int s, int t){

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

    if (visited[t])
      return f[t];
    else
      return 0;
}


void maxFlow(int s, int t){
    max_flow = 0;

    path_flow = bfs(s,t);
    while (path_flow >0){
        for (v=t; v != s; v=p[v]){
            u = p[v];
            rg[u][v] -= path_flow;
            rg[v][u] += path_flow;
        }
        max_flow += path_flow;
	      path_flow = bfs(s,t);
    }

    // build list of edges with flow
    vector<edge> used_edges;
    for (int i=0;i<n;i++){
			for (int j=0;j<m;j++){
					// if there is an edge and we used it at all ...
				if (PilarMap[i][j] != '0'){
					used_edges.push_back(edge(i, j, PilarMap[i][j] - '0'));
				}
			}
		}

		for(int x = 0; x < n; x++){
			for(int y = 0; y < m; y++){
				if(LizardMap[x][y] == 'L'){
					int x = (x * m + y) * 2;
					used_edges.push_back(edge(s, x, 1));
				}
			}
		}

    //cout << n << " " << max_flow << " " << used_edges.size() << endl;
    /*for (int i=0;i<used_edges.size();i++)
    	cout << used_edges[i].u << " " << used_edges[i].v << " " << used_edges[i].w << endl;*/

		max_flow = used_edges[0].u;

		cout << max_flow << endl;
}

/*void createGraph(int d, int n, int m, int source, int sink){
	for(int x = 0; x < n; x++){
		for(int y = 0; y < m; y++){
			//do something here
		}
	}
}*/

int main(){
  int testCases;
  string line;

  cin >> testCases;

  for(int x = 0; x < testCases; x++){
    cin >> n >> d;

    for(int i = 0; i < n; i++){
      cin >> PilarMap[i];
    }

    for(int j = 0; j < n; j++){
      cin >> LizardMap[j];
    }

      //get m for size n x m grid
    m = strlen(PilarMap[0]);
    int source = m * n * 2;
    int sink = source + 1;
		cout << source << " " << sink << endl;
		maxFlow(source, sink);
    //createGraph(d, n, n, source, sink);
  }

  return 0;
}
