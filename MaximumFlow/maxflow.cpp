#include <iostream>
#include <vector>
#include <queue>
#include <climits>
#include <cstring>
using namespace std;

struct edge{
	int u, v, w;
	edge(int _u, int _v, int _w): u(_u), v(_v), w(_w){}
};

#define N 505
int n,m,s,t,u,v,w;
long long g[N][N], // original graph
          rg[N][N],//  residual network
          f[N],    // flow to nodes
          max_flow, path_flow;

int p[N];  // parent of each node along new residual path

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
    for (int i=0;i<n;i++)
    	for (int j=0;j<n;j++)
	        // if there is an edge and we used it at all ...
    		if (g[i][j] > 0 && rg[i][j] < g[i][j])
    			used_edges.push_back(edge(i,j,g[i][j]-rg[i][j]));

    cout << n << " " << max_flow << " " << used_edges.size() << endl;
    for (int i=0;i<used_edges.size();i++)
    	cout << used_edges[i].u << " " << used_edges[i].v << " " << used_edges[i].w << endl;
}

int main(int argc, char *argv[]){
	cin >> n >> m >> s >> t;
	memset(g,0,sizeof(g));
	memset(rg,0,sizeof(rg));
	for (int i=0;i<m;i++){
		cin >> u >> v >> w;
		g[u][v] = rg[u][v] = w;
	}
	maxFlow(s,t);
	return 0;
}
