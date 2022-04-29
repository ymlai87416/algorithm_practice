# Data structure


## Union find

Shortcut: ds.uf

Time complexity: O(α(n))

Memory complexity: O(n)

```java
class UnionFind { // OOP style
    int[] p;
    int[] rank;
    int[] size;

    UnionFind(int N) {
        p = new int[N];
        rank = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) p[i] = i;
    }

    void init(){
        for (int i = 0; i < p.length; i++) p[i] = i;
        Arrays.fill(rank, 0);
        Arrays.fill(size, 1);
    }
    int findSet(int i) { return (p[i] == i) ? i : (p[i] = findSet(p[i])); }

    boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

    void unionSet(int i, int j) {
        if (!isSameSet(i, j)) { // if from different set
            int x = findSet(i), y = findSet(j);
            if (rank[x] > rank[y]) {
                p[y] = x; // rank keeps the tree short
                size[x] += size[y];
            }
            else {
                p[x] = y;
                size[y] += size[x];
                if (rank[x] == rank[y])
                    rank[y]++;
            }
        }
    }

    int getSetSize(int i){
        return size[i];
    }
};
```

Refer to: [UVA11503](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/DataStructure/UnionFind/UVA11503.java)

## Segment tree

Segment tree support online update and query
For range update, lazy propagation is needed

Shortcut: ds.st

Time complexity: 
- Build: O(nlogn)
- Query: O(log n)
- Update: O(log n)

Memory: O(n)

```java

```

- Lazy operation: push()

Refer to: [UVA11402](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/DataStructure/TreeDataStructure/UVA11402.java)

## 2D Segment tree

Shortcut: ds.st2d

```
```

Refer to: [UVA11297](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/DataStructure/TreeDataStructure/UVA11297.java)

## Fenwick tree

Shortcut: ds.ft

Time complexity: 
- Build: O(nlogn), but calling adjust on each element.
- Query: O(log n)
- Update: O(log n)

Memory: O(n) - less memory compare to segment tree

```java
class FenwickTree {
    private int[] ft; // recall that vi is: typedef vector<int> vi;
    public FenwickTree(int n) { ft = new int[n+1]; } // init n + 1 zeroes
    private int LSOne(int S){ return S & -S;}
    int rsq(int b) { // returns RSQ(1, b)
        int sum = 0; for (; b != 0; b -= LSOne(b)) sum += ft[b];
        return sum; } // note: LSOne(S) (S & (-S))
    int rsq(int a, int b) { // returns RSQ(a, b)
        return rsq(b) - (a == 1 ? 0 : rsq(a - 1)); }
    // adjusts value of the k-th element by v (v can be +ve/inc or -ve/dec)
    void adjust(int k, int v) { // note: n = ft.size() - 1
        for (; k < ft.length; k += LSOne(k)) ft[k] += v; }
};
```

Refer to [UVA12086](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/DataStructure/TreeDataStructure/UVA12086.java)