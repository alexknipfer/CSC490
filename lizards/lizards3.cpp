#include <iostream>
#include <string>
#include <vector>
#include <queue>
#include <climits>

using namespace std;

const int maxn = 25;
const int inf = 0x3f3f3f3f;
char Pilar[maxn][maxn];
char Lizard[maxn][maxn];
int num;
int n, m;
struct Edge{
    int from, to, cap, flow;
};
vector<Edge> edge;
vector<int> G[2*maxn*maxn];
int p[N];
long long g[maxn][maxn],
					rg[maxn][maxn],
					f[maxn],
					max_flow, path_flow;

void add_edge(int from, int to, int cap, int flow){
    edge.push_back((Edge){from, to, cap, flow});
    edge.push_back((Edge){to, from, 0, 0});
    int m = edge.size();
    G[from].push_back(m-2);
    G[to].push_back(m-1);
}

int vis[2*maxn*maxn];
int d[2*maxn*maxn];
int cur[2*maxn*maxn];

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

    if (visited[t]){
			return f[t];
		}
    else
      return 0;
}


int maxFlow(int s, int t){
    max_flow = 0;

    path_flow = bfs(s,t);
    while (path_flow > 0){
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

		//max_flow = used_edges[1].u;

		return max_flow;
}

bool is_safe(int x, int y, int d){
    if(x-d < 0 || x+d >= n || y-d < 0 || y+d >= m) return true;
    else return false;
}


int dist(int i, int j, int x, int y){
  return abs(i-x) + abs(j-y);
}

void build_graph(int d, int src, int des){
    for(int i = 0; i < n; i ++){
        for(int j = 0; j < m; j ++){
            if(Pilar[i][j] != '0'){
              int x = 2*(i*m+j);
                add_edge(x, x^1, Pilar[i][j]-'0', 0);
                if(is_safe(i, j, d)) {
                    add_edge(x^1, des, inf, 0);
                }
            }
        }
    }
    num = 0;
    for(int i = 0; i < n; i ++){
      for(int j = 0; j < m; j ++){
        if(Lizard[i][j] == 'L'){
            num ++;
            int x = 2*(i*m+j);
            add_edge(src, x, 1, 0);
        }
      }
    }
    for(int i = 0; i < n; i ++){
      for(int j = 0; j < m; j ++){
        if(Pilar[i][j] > '0'){
          for(int k1 = 0; k1 < n; k1 ++){
            for(int k2 = 0; k2 < m; k2 ++){
             if(k1 == i && k2 == j) continue;
             if(dist(i, j, k1, k2) <= d && Pilar[k1][k2] > '0'){
                int x = 2*(i*m+j);
                int y = 2*(k1*m+k2);
                add_edge(x^1, y, inf, 0);
             }
            }
          }
        }
      }
    }
}

int main(){

  int t, d;
  cin >> t;
  for(int test = 1; test <= t; test ++){
      cin >> n >> d;
      for(int i = 0; i < n; i ++)
          cin >> Pilar[i];
      for(int i = 0; i < n; i ++)
          cin >> Lizard[i];
      m = strlen(Pilar[0]);
      int src, des;
      src = 2*m*n;
      des = src+1;
      build_graph(d, src, des);
      //int ans = Dinic(src, des);
      //printf("Case #%d: ", test);
      //if(num-ans == 1) printf("%d lizard was left behind.\n", num-ans);
      //else if(num-ans == 0) printf("no lizard was left behind.\n");
      //else printf("%d lizards were left behind.\n", num-ans);
  }


  return 0;
}
