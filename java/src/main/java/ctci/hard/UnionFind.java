package ctci.hard;

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
        }
    }

    public int findSet(int a){
        if(p[a] != a)
            p[a] = findSet(p[a]);
        return p[a];
    }

    public void setSize(int a, int s){
        this.size[a] = s;
    }

    public int getSize(int a){
        return this.size[a];
    }

    public void unionSet(int a, int b){
        int x = findSet(a);
        int y = findSet(b);
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
