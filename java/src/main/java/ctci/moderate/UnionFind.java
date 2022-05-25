package ctci.moderate;

public class UnionFind{
    int[] p;
    int[] rank;
    int[] size;

    public UnionFind(int N){
        p = new int[N];
        rank = new int[N];
        size = new int[N];

        for(int i=0; i<N; ++i){
            p[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }

    public int findSet(int a){
        if(p[a] != a)
            p[a] = findSet(p[a]);
        return p[a];
    }

    public int getSize(int a){
        int x = findSet(a);
        return size[x];
    }

    public void unionSet(int a, int b){
        int x = findSet(a);
        int y = findSet(b);
        if(x == y) return;

        if(rank[x] > rank[y]){
            p[y] = x;
            size[x] += size[y];
        }
        else{
            p[x] = y;
            size[y] += size[x];
            if(rank[x] == rank[y])
                rank[y]++;
        }
    }
}
