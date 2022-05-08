# Graph

## Data structure

### Adj list
Shortcut: graph.adjlist

```java
class Pair implements Comparable<Pair> {
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(Pair o) {
        if (first < o.first) return -1;
        else if (first > o.first) return 1;
        else {
            if (second < o.second) return -1;
            else if (second > o.second) return 1;
            return 0;
        }
    }
}

static ArrayList<Pair>[] adj = new ArrayList[20001];
for (int i = 0; i < adj.length; ++i)
    adj[i] = new ArrayList<Pair>();
```

### Adj Matrix

Shortcut: graph.adjlist

```java
static int[][] AdjMat = new int[101][101];
```

### Edge list

Use by Kruskal algorithm, which sort the edge according to weighting.

```java
class Tuple implements Comparable<Tuple>{
    int first;
    int second, third;
    public Tuple(int a, int b, int c){
        first = a;
        second = b;
        third = c;
    }

    @Override
    public int compareTo(Tuple o) {
        if(first < o.first) return -1;
        else if(first > o.first) return 1;
        else{
            if(second < o.second) return -1;
            else if(second > o.second) return 1;
            else{
                if(third < o.third) return -1;
                else if(third > o.third) return 1;
                else return 0;
            }
        }
    }
}

Stack<Tuple> EdgeList = new Stack<Tuple>(); // (weight, two vertices) of the edge
```

## BFS 

Only iterative version, no recursive version.

Time complexity: O(V + E)
Shortcut: graph.bfs

```java
//TODO: fix it ASAP
int INF = 1_000_000_000
Arrays.fill(d, INF);

Queue<Integer> queue = new ArrayDeque<>();

d[s] = 0;
queue.add(s);

while(!queue.isEmpty()){
    int u = queue.poll();

    for(int p=0; p<adjList[u].size(); ++p){
        Pair v = adjList[u].get(p);
        if(d[v] == INF){
            d[v.first] = d[u] + 1;
            queue.add(v.first);
        }
    }
}
```

Refer: [UVA10986](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/Graph/WeightedDijkstraEasier/UVA10986.java)

### Meet in the middle

```java
//TODO: implement
```

Refer: [UVA11212](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/MoreAdvanceTopic/MeetInTheMiddle_A_IDA/UVA11212.java)

## DFS

Time complexity: O(V+E)
Shortcut: graph.dfs.it, graph.dfs.r

```java
//iterative version
public static void dfs(int s) {
    visited = new boolean[V];

    // depth-first search using an explicit stack
    Stack<Integer> stack = new Stack<Integer>();
    visited[s] = true;
    stack.push(s);
    while (!stack.isEmpty()) {
        int v = stack.peek();
        int w = -1;
        for(int i=0; i<adjcnt[v]; ++i) {
            if (!visited[adj[v][i]])
                w = adj[v][i]; break;
        }
        if(w != -1){
            visited[w] = true;
            stack.push(w);
        }
        else {
            stack.pop();
        }
    }
}
```

```java
int[] dfs_num = new int[V];
Arrays.fill(dfs_num, UNVISITED);
void dfs(int u) { // DFS for normal usage: as graph traversal algorithm
    dfs_num[u] = VISITED; // important: we mark this vertex as visited
    for (int j = 0; j < AdjList[u].size(); j++) { // default DS: AdjList
        ii v = AdjList[u].get(j); // v is a (neighbor, weight) pair
        if (dfs_num[v.first] == UNVISITED) // important check to avoid cycle
            dfs(v.first); // recursively visits unvisited neighbors of vertex u
    }
} // for simple graph traversal, we ignore the weight stored at v.second
```

### iterative deepening depth-first search

Use bfs instead, not much use case here.

## connected component

Connected component: do a dfs from each vertex, the visited components are the connected components.

or we can do a union find

## Strongly connected component

Time complexity: O(|V|+|E|).

If graph is smaller, can also use Floyd-Warshall algorithm.

```java

/*
You can get the number of scc and the corresponding root.
init with
Arrays.fill(dfs_num, UNVISITED);
Arrays.fill(dfs_low, 0);
Arrays.fill(visited, false);
dfsNumberCounter = numSCC = 0;
*/

Stack<Integer> S;
int dfsNumberCounter;
int[] dfs_low;
boolean[] visited;
int[] dfs_num;
List<List<Pair>> AdjList;
int numSCC;
final  int UNVISITED = -1;
void tarjanSCC(int u) {
    dfs_low[u] = dfs_num[u] = dfsNumberCounter++; // dfs_low[u] <= dfs_num[u]
    S.push(u); // stores u in a vector based on order of visitation
    visited[u] = true;
    for (int j = 0; j < (int)AdjList.get(u).size(); j++) {
        Pair v = AdjList.get(u).get(j);
        if (dfs_num[v.first] == UNVISITED)
            tarjanSCC(v.first);
        if (visited[v.first]) // condition for update
            dfs_low[u] = Math.min(dfs_low[u], dfs_low[v.first]); }
    if (dfs_low[u] == dfs_num[u]) { // if this is a root (start) of an SCC
        ++numSCC;
        //System.out.format("SCC %d:", numSCC); // this part is done after recursion
        while (true) {
            int v = S.pop(); visited[v] = false;
            //System.out.format(" %d", v);
            if (u == v) break; }
        //System.out.print("\n");
    }
}
```

## flood fill

Assume a 2d map, we want to flood-fill.

```java
int dr[] = {1,1,0,-1,-1,-1, 0, 1}; // trick to explore an implicit 2D grid
int dc[] = {0,1,1, 1, 0,-1,-1,-1}; // S,SE,E,NE,N,NW,W,SW neighbors

int floodfill(char[][] grid, int r, int c, char c1, char c2) { // returns the size of CC
    int R=grid.length; int C=R > 0? grid[0].length: 0;
    if (r < 0 || r >= R || c < 0 || c >= C) return 0; // outside grid
    if (grid[r][c] != c1) return 0; // does not have color c1
    int ans = 1; // adds 1 to ans because vertex (r, c) has c1 as its color
    grid[r][c] = c2; // now recolors vertex (r, c) to c2 to avoid cycling!
    for (int d = 0; d < 8; d++)
        ans += floodfill(grid, r + dr[d], c + dc[d], c1, c2);
    return ans; // the code is neat due to dr[] and dc[]
}

```

## Minimum spanning tree

### Kruskal

Time complexity: 
Shortcut: graph.kruskal


```java
//require tuple
//require union find
public int kruskal(ArrayList<Tuple> EdgeList, ArrayList<Tuple> result){
    Collections.sort(EdgeList);

    int mst_cost = 0;

    UnionFind UF = new UnionFind(m);
    UF.init();

    for (int i = 0; i < n; i++) {
        Tuple front = EdgeList.get(i);
        if (!UF.isSameSet(front.second, front.third)) {
            mst_cost += front.first;
            result.add(front);
            UF.unionSet(front.second, front.third);
        }
    }

    return mst_cost;
}
```

Refer: [UVA10600](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/Graph/MSTVariants/UVA10600.java)

### Prim

TODO: check with cp3

Time complexity:
Shortcut: graph.prim

```java

```

## Shortest path

### Single source shortest path

#### BFS

```java
//this version with distance
Arrays.fill(dist, INF);
dist[s] = 0; // INF = 1Billion to avoid overflow

PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
pq.offer(new Pair(0, s));
while (!pq.isEmpty()) { // main loop
    Pair front = pq.poll();
    int d = front.first, u = front.second;
    if (d > dist[u]) continue; // this is a very important check
    for (int j = 0; j < adj[u].size(); j++) {
        Pair v = adj[u].get(j); // all outgoing edges from u
        if (dist[u] + v.second < dist[v.first]) {
            dist[v.first] = dist[u] + v.second; // relax operation
            pq.offer(new Pair(dist[v.first], v.first));
        }
    }
} // this variant can cause duplicate items in the priority queue
```

#### Dijkstra

Time complexity: O((V+E) logV)
Shortcut: graph.dij

```java
//require pair
static int[] dist = new int[102];
static final int INF = 1000000000;

//the result is in dist array
public void dijkstra(int e){
    Arrays.fill(dist, INF);
    dist[e] = 0; // INF = 1Billion to avoid overflow

    PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
    pq.offer(new Pair(0, e));
    while (!pq.isEmpty()) { // main loop
        Pair front = pq.poll();
        int d = front.first, u = front.second;
        if (d > dist[u]) continue; // this is a very important check
        for (int j = 0; j < adj[u].size(); j++) {
            Pair v = adj[u].get(j); // all outgoing edges from u
            if (dist[u] + v.second < dist[v.first]) {
                dist[v.first] = dist[u] + v.second; // relax operation
                pq.offer(new Pair(dist[v.first], v.first));
            }
        }
    } // this variant can cause duplicate items in the priority queue
}

```

### Bellman ford

Can handle negative cycle

Time complexity: O(VE)

```java
ArrayList<Pair>[] adj = new ArrayList[201]; 
final int INF = 1_000_000_000;
int[] dist = new int[201];

//place the source at 0
public bellman_ford(int S){
    Arrays.fill(dist, INF);
    dist[S] = 0;
    for (int i = 0; i < n - 1; i++) // relax all E edges V-1 times
        for (int u = 0; u < n; u++) // these two loops = O(E), overall O(VE)
            for (int j = 0; j < adj[u].size(); j++) {
                Pair v = adj[u].get(j); // record SP spanning here if needed
                dist[v.first] = Math.min(dist[v.first], dist[u] + v.second); // relax
            }
}

```

Refer: [UVA10449](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/Graph/NegativeWeightCycleBellmanFord/UVA10449.java)

### Shortest Path Faster Algorithm

This algorithm run as fast as Dijkstra, but it can handle negative cycle.

Time complexity: O(k * E)
Shortcut: graph.spfa

//TODO: To be completed

```java

```


### Floyd Warshall - All pair shortest path

Time complexity: O(V^3)
Shortcut: graph.floyd

```java
Can be used to 
* detected the negative cycle
* SCC => if Adj[i][j] and Adj[j][i] are not INF, they belongs to same SCC.
* Radius of the graph
* Minmax

```java
static final int INF = 1000000000;

public void flyodWarshall(){
    // inside int main()
    // precondition: AdjMat[i][j] contains the weight of edge (i, j)
    // or INF (1B) if there is no such edge
    // AdjMat is a 32-bit signed integer array
    for (int k = 0; k < V; k++) // remember that loop order is k->i->j
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                AdjMat[i][j] = Math.min(AdjMat[i][j], AdjMat[i][k] + AdjMat[k][j]);
}

```

Refer: [UVA11463](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/Graph/AllPairShortestPathStandard/UVA11463.java)

#### Print shortest path

Shortcut: graph.floyd.print

```java
static final int INF = 1000000000;

public void flyodWarshall(){
    // inside int main()
    // precondition: AdjMat[i][j] contains the weight of edge (i, j)
    // or INF (1B) if there is no such edge
    // AdjMat is a 32-bit signed integer array
    for (int k = 0; k < V; k++) // remember that loop order is k->i->j
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                if((AdjMat[i][k] + AdjMat[k][j] < AdjMat[i][j]) {
                    AdjMat[i][j] = AdjMat[i][k] + AdjMat[k][j];
                    p[i][j] = p[k][j]
                }
}

void printPath(int i, int j) {
    if (i != j) printPath(i, p[i][j]);
    System.out.print(" " + j);
}
```


## Articulation point and bridges

Time complexity: O(V + E)

```java
int dfsNumberCounter = 0;
List<List<Pair>> AdjList = new ArrayList<>();
var dfs_num = new int[V];
var dfs_low = new int[V];
var dfs_parent = new int[V];
var articulation_vertex = new boolean[V];

final static int UNVISITED = -1;

Arrays.fill(dfs_num, UNVISITED);
Arrays.fill(dfs_low, 0);
Arrays.fill(dfs_parent, 0);
Arrays.fill(articulation_vertex, false);

void articulationPointAndBridge(int u) {
    dfs_low[u] = dfs_num[u] = dfsNumberCounter++; // dfs_low[u] <= dfs_num[u]
    for (int j = 0; j < (int)AdjList.get(u).size(); j++) {
        Pair v = AdjList.get(u).get(j);
        if (dfs_num[v.first] == UNVISITED) { // a tree edge
            dfs_parent[v.first] = u;
            if (u == dfsRoot) rootChildren++; // special case if u is a root
            articulationPointAndBridge(v.first);
            if (dfs_low[v.first] >= dfs_num[u]) // for articulation point
                articulation_vertex[u] = true; // store this information first
            //if (dfs_low[v.first] > dfs_num[u]) // for bridge
            //    bridges.add(new Pair(Math.min(u, v.first), Math.max(u, v.first)));
            dfs_low[u] = Math.min(dfs_low[u], dfs_low[v.first]); // update dfs_low[u]
        }
        else if (v.first != dfs_parent[u]) // a back edge and not direct cycle
            dfs_low[u] = Math.min(dfs_low[u], dfs_num[v.first]); // update dfs_low[u]
    }
}
```

Refer: [UVA10765](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/Graph/ArticulationPoints/UVA10765.java)

## Network flow

Find the max flow of the graph, the value is also equal to the min cut.

Can also apply on bipartite grpah, result in maximum cardinality bipartite matching. (each edge weight is 1)

### Ford Fulkerson

Not a good candidate for contest.
Runtime on UVA11380: 0.950ms

Time complexity: O(f* E) where f* = maximum flow
Shortcut: graph.ff

```java
int[][] res = new int[maxV][maxV];
int mf, f, s, t; // global variables
int[] p; // p stores the BFS spanning tree from s
void augment(int v, int minEdge) { // traverse BFS spanning tree from s->t
    if (v == s) { f = minEdge; return; } // record minEdge in a global var f
    else if (p[v] != -1) {
        augment(p[v], Math.min(minEdge, res[p[v]][v]));
        res[p[v]][v] -= f; res[v][p[v]] += f;
    }
}

BitSet vis;

int fulkerson(int sIdx, int tIdx) {
    s = sIdx;
    t = tIdx;
    // inside int main(), assume that we have both res (AdjMatrix) and AdjList
    mf = 0;
    while (true) { // now a true O(VE^2) Edmonds Karp’s algorithm
        f = 0;
        BitSet vis = new BitSet(maxV); vis.set(s); // we change vi dist to bitset!
        p = new int[maxV]; Arrays.fill(p, -1);
        dfs(s);
        
        Queue<Integer> q = new ArrayDeque<>(); q.offer(s);
        
        augment(t, INF);
        if (f == 0) break;
        mf += f;
    }
    
    return mf;
}

dfs(int u){
    for (int j = 0; j < AdjList.get(u).size(); j++) { // AdjList here!
        int v = AdjList.get(u).get(j); // we use vector<vi> AdjList
        if (res[u][v] > 0 && !vis.get(v)) {
            vis.set(v);
            p[v] = u;
            dfs(v);
        }
    }
}
```

### Edmond Karp

Runtime on UVA11380: 0.880ms

Time complexity: O(VE^2)
Shortcut: graph.ek

```java
int[][] res = new int[maxV][maxV];
int mf, f, s, t; // global variables
int[] p; // p stores the BFS spanning tree from s
void augment(int v, int minEdge) { // traverse BFS spanning tree from s->t
    if (v == s) { f = minEdge; return; } // record minEdge in a global var f
    else if (p[v] != -1) {
        augment(p[v], Math.min(minEdge, res[p[v]][v]));
        res[p[v]][v] -= f; res[v][p[v]] += f;
    }
}

int edmondKarp(int sIdx, int tIdx) {
    s = sIdx;
    t = tIdx;
    // inside int main(), assume that we have both res (AdjMatrix) and AdjList
    mf = 0;
    while (true) { // now a true O(VE^2) Edmonds Karp’s algorithm
        f = 0;
        BitSet vis = new BitSet(maxV); vis.set(s); // we change vi dist to bitset!
        Queue<Integer> q = new ArrayDeque<>(); q.offer(s);
        p = new int[maxV]; Arrays.fill(p, -1);
        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == t) break;
            for (int j = 0; j < AdjList.get(u).size(); j++) { // AdjList here!
                int v = AdjList.get(u).get(j); // we use vector<vi> AdjList
                if (res[u][v] > 0 && !vis.get(v)) {
                    vis.set(v);
                    q.offer(v);
                    p[v] = u;
                }
            }
        }
        augment(t, INF);
        if (f == 0) break;
        mf += f;
    }
    
    return mf;
}
```

Refer: [UVA11380](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/Graph/NetworkFlow/Variants/UVA11380.java)


### Dinic

Runtime on UVA11380: 1.170ms

Time complexity: O(V^2 E)
Shortcut: graph.dinic

```java
//new code at: https://github.com/stevenhalim/cpbook-code/blob/master/ch8/maxflow.java

int[] d;
int dinic(int sIdx, int tIdx) {
    s = sIdx;
    t = tIdx;
    // inside int main(), assume that we have both res (AdjMatrix) and AdjList
    mf = 0;
    while (true) { // now a true O(VE^2) Edmonds Karp’s algorithm
        f = 0;
        vis = new BitSet(maxV); vis.set(s); // we change vi dist to bitset!
        Queue<Integer> q = new ArrayDeque<>(); q.offer(s);
        d = new int[maxV]; d[s] = 0;
        p = new int[maxV]; Arrays.fill(p, -1);
        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == t) break;
            for (int j = 0; j < AdjList.get(u).size(); j++) { // AdjList here!
                int v = AdjList.get(u).get(j); // we use vector<vi> AdjList
                if (res[u][v] > 0 && !vis.get(v)) {
                    vis.set(v);
                    q.offer(v);
                    d[v] = d[u] + 1;
                    p[v] = u;
                }
            }
        }

        int sf = 0;
        while(true){
            f = 0;
            augment(t, INF);
            if (f == 0) break;
            sf += f;

            vis = new BitSet(maxV); vis.set(s);
            p = new int[maxV]; Arrays.fill(p, -1);
            dfs2(s);
        }

        if(sf == 0) break;
        mf += sf;
    }
    
    return mf;
}

void dfs2(int u){
    for (int j = 0; j < AdjList.get(u).size(); j++) { // AdjList here!
        int v = AdjList.get(u).get(j); // we use vector<vi> AdjList
        if (res[u][v] > 0 && !vis.get(v) && d[v] == d[u]+1) {
            vis.set(v);
            p[v] = u;
            dfs(v);
        }
    }
}
```

### Find min-cut nodes

```java 
//TODO: to be completed
```


## bipartite graph

### Check

Use BFS to do the checking.

Time complexity: O(V+E)

```java
int INF = 1_000_000_000
Arrays.fill(color, INF);

Queue<Integer> queue = new ArrayDeque<>();
bool isBipartite = true; 
color[s] = 0;
queue.add(s);

while(!queue.isEmpty() & isBipartite){
    int u = queue.poll();

    for(int p=0; p<adjList[u].size(); ++p){
        Pair v = adjList[u].get(p);
        if(visited[v] == INF){
            color[v.first] = 1 - color[u];
            queue.add(v.first);
        }
        else if(color[v.first] == color[u]){
            isBipartite = false;
            break;
        }
    }
}
```

### Hopcroft Karp's algorithm

You can add a source and sink to the graph, and run Edmonds Karp.

Time complexity: O(sqrt(V) * E)

```java
//TODO: to be implemented
```

[Test](https://leetcode.com/problems/maximum-students-taking-exam/discuss/1734630/C%2B%2B-Two-solutions%3A-Optimized-bitmask-DP-and-Hopcroft-Karp-Bipartite-MIS) 

## DAG
## Topological sort

Linear ordering of vertices such taht for every directed edge uv, vertex u comes before v in the ordering.

### DFS

If any ordering will do, prefer this algorithm

Time complexity: O(V+E)

```java
int EXPLORED = 1;
int VISITED = 2;
int UNVISITED = 0;

ts = new ArrayList<Integer>();
for(int i=0; i<numCourses; ++i){
    //do dfs if not visted
    if(visited[i] == UNVISITED){
        boolean hasCycle = dfs(i);
        if(hasCycle) 
            System.out.println("Have cycle, no topological sort");
    }
}
Collections.reverse(ts);
ArrayList<Integer> result =ts;

boolean dfs(int u){
    visited[u] = EXPLORED;
    
    for(int i=0; i<adjList[u].size(); ++i){
        int v = adjList[u].get(i);
        //System.out.println("" + u + "->" + v + ":" + visited[v]);
        if(visited[v] == EXPLORED) return true;
        if(visited[v] == VISITED) continue;
        boolean hasCycle = dfs(v);
        if(hasCycle) return true;
    }
    order.add(u);
    
    visited[u] = VISITED;
    return false;
}
```

Refer: [UVA11060](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/Graph/TopologicalSort/UVA11060.java)

Refer: [Course Schedule II](https://leetcode.com/submissions/detail/691062853/)

### Kahn's algorithm

When additional requirement like take the one which appears first in the input. This algorithm works.

Time complexity: O(V+E)

```java
ArrayList<Integer> Kahn(int V) {
    //this either sort all, or fail to sort any.
    PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
    ArrayList<Integer> answer = new ArrayList<Integer>();

    for(int i=0; i<V; ++i)
        if(indegree[i] == 0){
            queue.offer(i);
            indegree[i]--;
        }
    while(!queue.isEmpty()){
        int u= queue.poll();

        for(int j=0; j<adjcnt[u]; ++j){
            indegree[adj[u][j]]--;
        }

        for(int i=0; i<adjcnt[u]; ++i){
            int v = adj[u][i];
            if(indegree[v] == 0) {
                queue.offer(v);
                indegree[v]--;
            }
        }
        answer.add(u);
    }
    return answer;
}
```

Refer: [UVA11060](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/Graph/TopologicalSort/UVA11060.java)

## Graph matching

### Unweighted MCBM

Hopcroft Karp
Time complexity: O(E*sqrt(V))

```java

```


Refer: [Maximum Number of Accepted Invitations]()

### Weighted MCBM

Hungarian algorithm / Kuhn Munkres’s algorithm
Time complexity: O(V^3)

```java

```

Refer: [Maximum Number of Accepted Invitations]()

### Unweighted MCM

Edmond algorithm
Time complexity: 

if small, apply the DP is also possible.

```java
//TOOD: to be completed
```

### Weighted MCM
DP with bitmask

```java
//required numberOfSetBits
//set 1 to node which need to be matched
//second path contained the weight of the edge
private int weightedMCM(int bitmask){
    //pair up someone still not in a group and then return
    if(dp[bitmask] != INF) return dp[bitmask];
    if(NumberOfSetBits(bitmask) == N) return 0;

    int result = INF;
    for (int i = 0; i < N; i++) {
        if((bitmask & (1 << i)) != 0) continue;
        for (Pair p: adjList[i]) {
            if(i==p.first) continue;
            if((bitmask & (1 << p.first)) != 0) continue;
            int temp = dpHelper(bitmask | (1 << i) | (1 << p.first)) + p.second;
            if(temp < result)
                result = temp;
        }
    }

    dp[bitmask] =result;
    return result;
}
```

## Tree

### Minimum vertex cover tree

### Display order

#### Post

Post order iterative is a little bit difficult in stack. 
1. You have to make it in some way, either push it 2 times, or push it with the right under it.


Shortcut: tree.post.r, tree.post.it

```java
private void postOrder(TreeNode node) { 
    if (node == null) { return; } 
    postOrder(node.left); 
    postOrder(node.right); 
    System.out.printf("%s ", node.data); 
}
```

```java
ArrayList<Integer> postOrderIterative(Node node) {
    Stack<Node> S = new Stack<Node>();
    ArrayList<Integer> list = new ArrayList<>();
        
    // Check for empty tree
    if (node == null)
        return list;
    S.push(node);
    Node prev = null;
    while (!S.isEmpty())
    {
        Node current = S.peek();

        /* go down the tree in search of a leaf an if so process it
        and pop stack otherwise move down */
        if (prev == null || prev.left == current ||
                                    prev.right == current) {
            if (current.left != null)
                S.push(current.left);
            else if (current.right != null)
                S.push(current.right);
            else {
                S.pop();
                list.add(current.data);
            }

            /* go up the tree from left node, if the child is right
            push it onto stack otherwise process parent and pop
            stack */
        } else if (current.left == prev) {
            if (current.right != null)
                S.push(current.right);
            else {
                S.pop();
                list.add(current.data);
            }
                
            /* go up the tree from right node and after coming back
            from right node process parent and pop stack */
        } else if (current.right == prev) {
            S.pop();
            list.add(current.data);
        }
        prev = current;
    }

    return list;
}
```

#### In

Shortcut: tree.in.r, tree.in.it

```java
private void inorder(TreeNode node) { 
    if (node == null) { return; } 
    postOrder(node.left); 
    System.out.printf("%s ", node.data); 
    postOrder(node.right); 
}
```

```java
ArrayList<Integer> void inorder(){
    ArrayList<Integer> list = new ArrayList<>();
    if (root == null)
        return list;

    Stack<Node> s = new Stack<Node>();
    Node curr = root;

    // traverse the tree
    while (curr != null || s.size() > 0) {

        /* Reach the left most Node of the
        curr Node */
        while (curr !=  null) {
            /* place pointer to a tree node on
                the stack before traversing
                the node's left subtree */
            s.push(curr);
            curr = curr.left;
        }

        /* Current must be NULL at this point */
        curr = s.pop();

        list.add(curr.data);

        /* we have visited the node and its
            left subtree.  Now, it's right
            subtree's turn */
        curr = curr.right;
    }
}
```

Refer: [Kth Smallest Element in BST](https://leetcode.com/submissions/detail/691160478/)

#### Pre

Shortcut: tree.pre.r, tree.pre.it

```java
private void preorder(TreeNode node) { 
    if (node == null) { return; } 
    System.out.printf("%s ", node.data); 
    postOrder(node.left); 
    postOrder(node.right); 
}
```

```java
// An iterative process to print preorder traversal of Binary tree
ArrayList<Integer> preorder(Node node)
{
    ArrayList<Integer> list = new ArrayList<>();
    // Base Case    
    if (node == null) {
        return;
    }

    // Create an empty stack and push root to it
    Stack<Node> nodeStack = new Stack<Node>();
    nodeStack.push(root);

    /* Pop all items one by one. Do following for every popped item
        a) print it
        b) push its right child
        c) push its left child
        Note that right child is pushed first so that left is processed first */
    while (nodeStack.empty() == false) {

        // Pop the top item from stack and print it
        Node mynode = nodeStack.peek();
        list.add(mynode.data);
        nodeStack.pop();

        // Push right and left children of the popped node to stack
        if (mynode.right != null) {
            nodeStack.push(mynode.right);
        }
        if (mynode.left != null) {
            nodeStack.push(mynode.left);
        }
    }
}
```


## Euler graph

Although very similiar, Hamiltonian cycle, which pass through every nodes, is NP-complete!


### Check

To have a path: only 2 vertices have odd degree, and start and end from there
To have a tour: all verticies have even degree

### Cycle and Path

```java
//TODO: review
//in the adj graph, set second as 1 first
List<Integer> cyc;
public void EulerTour(int idx, int u) {
    for (int j = 0; j < (int)AdjList.get(u).size(); j++) {
        Pair v = AdjList.get(u).get(j);
        if (v.second != 0) { // if this edge can still be used/not removed
            v.second = 0; // make the weight of this edge to be 0 (‘removed’)
            for (int k = 0; k < AdjList.get(v.first).size(); k++) {
                Pair uu = AdjList.get(v.first).get(k);  // remove bi-directional edge once only.
                if (uu.first == u && uu.second != 0) {
                    uu.second = 0;
                    break;
                }
            }
            cyc.add(idx, u);
            EulerTour(idx+1, v.first);
        }
    }
}
```


## A*

```java
//TOOD: to be complete
```

### Iterative deepening A*

```java
//TOOD: to be complete
```

## Chinese postman problem

```java
//require dijsktra
//require weighted MCM
private int chinesePostman(int N, ArrayList<Pair>[] adjList) {
    int ans = 0;

    HashSet<Integer> oddNodes = new HashSet<>();
    //build a subgraph of odd node and do a minimum weight perfect matching
    for (int i = 0; i < N; i++) {
        int cnt = 0;
        for (int j = 0; j < adjList[i].size(); j++) {
            if(adjList[i].get(j).first == i) continue;
            else ++cnt;
        }
        if(cnt %2==1)
            oddNodes.add(i);
    }

    //dijkstra
    //new adjlist
    ArrayList<Pair>[] adjListOdd=  new ArrayList[N];
    for (int i = 0; i < N; i++) {
        adjListOdd[i] = new ArrayList<>();
    }

    this.N = N;
    for (int n: oddNodes) {
        int[] a = dijkstra(n, adjList);
        for (int m:oddNodes){
            if(n >= m) continue;
            adjListOdd[n].add(new Pair(m, a[m]));
            adjListOdd[m].add(new Pair(n, a[m]));
        }
    }

    this.adjList = adjListOdd;

    //new additional path from minimum weight matching
    for (int i = 0; i < dp.length; i++) {
        dp[i] = INF;
    }
    //set all none related nodes to 1
    int bitmask = 0;
    for (int i = 0; i < N; i++) {
        if(oddNodes.contains(i)) continue;
        bitmask = bitmask | (1 << i);
    }
    ans = weightedMCM(bitmask);

    //add up each path in original graph
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < adjList[i].size(); j++) {
            if(i <= adjList[i].get(j).first)
                ans  += adjList[i].get(j).second;
        }
    }

    return ans;
}
```

Refer: [UVA10296](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/RareTopic/ChinesePostmanProblem/UVA10296.java)

