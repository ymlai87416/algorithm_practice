public class dijkstra {
    
    public static void main(String[] args) {
        dijkstra d = new dijkstra();
        d.test();
    }

    //this is the test case 
    public void test(){
        int nTestA = 10;
        int nTestB = 10;

        Impl impl = new Impl();
        Ref ref = new Ref();

        for(int i=0; i<nTest; ++i){

            TestCase t = readTestcase(i);
            List<Integer> out1 = impl.dijkstra(t.adjList, t.source);
            List<Integer> out2 = ref.dijkstra(t.adjList, t.source);

            if(!compareOutput(out1, out2)){
                throw new RuntimeException("Test case " + i + " failed");
            }
        }

        for(int i=0; i<nTest; ++i){

            TestCase t = readTestcase(i);
            List<Integer> out1 = impl.bellmonford(t.adjList, t.source);
            List<Integer> out2 = bellmonford_ref(t.adjList, t.source);

            if(!compareOutput(out1, out2)){
                throw new RuntimeException("Test case " + i + " failed");
            }
        }
    }

    private TestCase readTestcase(int i){
       //do nothing 
       return null;
    }

    private boolean compareOutput(List<Integer> output1, List<Integer> output2){
        return true;
    }

}

class Impl{
    public List<Integer> dijkstra(List<List<Integer>> adjList, int source){
        return null;
    } 

    public List<Integer> bellmonford(List<List<Integer>> adjList, int source){
        return null;
    }
}

class Ref{

    //require pair
    static int[] dist = new int[102];
    static final int INF = 1000000000;

    public List<Integer> dijkstra(List<List<Integer>> adjList, int e){
        int n = adjList.size();
        
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
    
        List<Integer> res = new ArrayList<Integer>();
        for(int i=0; i<n; ++i){
            res.add(dist[i]);
        }
        return res;
    } 

    ArrayList<Pair>[] adj = new ArrayList[201]; 

    public List<Integer> bellmonford(List<List<Integer>> adjList, int S){
        int n = adjList.size();

        Arrays.fill(dist, INF);
        dist[S] = 0;
        for (int i = 0; i < n - 1; i++) // relax all E edges V-1 times
            for (int u = 0; u < n; u++) // these two loops = O(E), overall O(VE)
                for (int j = 0; j < adj[u].size(); j++) {
                    Pair v = adj[u].get(j); // record SP spanning here if needed
                    dist[v.first] = Math.min(dist[v.first], dist[u] + v.second); // relax
                }

        List<Integer> res = new ArrayList<Integer>();
        for(int i=0; i<n; ++i){
            res.add(dist[i]);
        }
        return res;

    }
}

class TestCase{
    List<List<Integer>> adjList;
    int source;
}


/*
To restore
*/