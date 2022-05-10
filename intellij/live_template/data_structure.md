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
class SegmentTree { // the segment tree is stored like a heap array
    private int[] st = new int[100001*4];
    private int[] A;
    private int n;

    private int left(int p) {
        return p << 1;
    } // same as binary heap operations

    private int right(int p) {
        return (p << 1) + 1;
    }

    private void build(int p, int L, int R) { // O(n)
        if (L == R) // as L == R, either one is fine
            st[p] = A[L];
        else { // recursively compute the values
            build(left(p), L, (L + R) / 2);
            build(right(p), (L + R) / 2 + 1, R);
            int p1 = st[left(p)], p2 = st[right(p)];
            st[p]= p1 * p2;
        }
    }

    /* This is individual update */
    public void update(int p, int val){
        update(1, p, val, 0, n-1);
    }

    public void update(int p, int idx, int val, int L, int R){
        if (L == R) // as L == R, either one is fine
            st[p] = val;
        else { // recursively compute the values
            if(L <= idx && idx <= (L+R)/2) update(left(p), idx, val, L, (L+R)/2);
            else if ((L+R)/2+1 <= idx && idx<= R)update(right(p), idx, val, (L+R)/2+1, R);
            int p1 = st[left(p)], p2 = st[right(p)];          //Never a node with only left or right child.
            st[p]= p1 * p2;
        }
    }

    /* Non optimized range update */
    public void updateRange(int i, int j, int val){
        updateRange(1, 0, n-1, i, j, val);
    }

    private void updateRange(int p, int L, int R, int i, int j, int val){
        if (L == R) // as L == R, either one is fine
            st[p] = val;
        else { // recursively compute the values
            if (L <= i && i <= (L + R) / 2)
                updateRange(left(p), L, (L + R) / 2, i, Math.min(j, (L + R) / 2), val);
            if ((L + R) / 2 + 1 <= j && j <= R)
                updateRange(right(p), (L + R) / 2 + 1, R, Math.max(i, (L + R) / 2 + 1), j, val);
            int p1 = st[left(p)], p2 = st[right(p)];          //Never a node with only left or right child.
            st[p] = p1 + p2;
        }
    }

    private int rmq(int p, int L, int R, int i, int j) { // O(log n)
        if (i > R || j < L) return Integer.MIN_VALUE; // current segment outside query range
        if (L >= i && R <= j) return st[p]; // inside query range
        // compute the min position in the left and right part of the interval
        int p1 = rmq(left(p), L, (L + R) / 2, i, j);
        int p2 = rmq(right(p), (L + R) / 2 + 1, R, i, j);
        if (p1 == Integer.MIN_VALUE) return p2; // if we try to access segment outside query
        if (p2 == Integer.MIN_VALUE) return p1; // same as above
        return p1 * p2; // as in build routine
    }

    public SegmentTree(int[] A, int size) {
        this.A = A;
        n = size;
        //Arrays.fill(st, 0);
        build(1, 0, n - 1); // recursive build
    }

    public int rmq(int i, int j) {
        return rmq(1, 0, n - 1, i, j);
    } // overloading
}

```

Refer to: []()

- Lazy operation: push()

Refer to: [UVA11402](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/DataStructure/TreeDataStructure/UVA11402.java)

- Segment tree on interval instead of update range of value.

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

## Heap

Not much used, can be replaced by PriorityQueue in most setting.

Update element in PriorityQueue replaced by either delete O(N) and insert again O(logN)
or being lazy and check if the element pop from PriorityQueue is the same as the latest record.

Application: 
It is inferor to linear scan if the scan is small.
Refer to [Longest Repeating Character Replacement](https://leetcode.com/submissions/detail/692559183/), which linear scan perform much faster.

TODO: There is currently no bubbleUp and bubbleDown implementation in my implementation of heap.

Refer: [Kth Largest Element in a Stream](https://leetcode.com/submissions/detail/627876806/)


### Quick select

Most Top K problem can also be solved by quick select, which have a better time complexity of O(N)

```java
int[] nums;
public int findKthLargest(int[] nums, int k) {
    this.nums = nums;
    return quickSelect(0, nums.length-1, nums.length-k);
}

Random r = new Random();

//k means position of desired element in sorted array.
int quickSelect(int left, int right, int k){
    //base case
    if(left == right) return nums[left];
    //choose a pivot index by random
    int pivotIndex = r.nextInt(right-left) + left;
    //call parititon
    pivotIndex = partition(left, right, pivotIndex);
    //from partition we know pivot is nth bigger
    //if n == k return pivot
    if(k == pivotIndex)
        return nums[k];
    //else if n < k => quickSelect(left, pivot-1, k)
    else if(k < pivotIndex)
        return quickSelect(left, pivotIndex-1, k);
    //else if n > k => quickSelect(pivot+1, right, k) 
    else
        return quickSelect(pivotIndex+1, right, k);
}

//left and right are inclusive
int partition(int left, int right, int pivotIndex){
    //create a variable call store index
    int storeIndex = left;
    int pivot = nums[pivotIndex];
    //move pivot to the rightmost
    swap(right, pivotIndex);
    //loop all the element and store the element smaller than pivot to left side (store index)
    for(int i=left; i<=right; ++i){
        if(nums[i] < pivot){
            swap(i, storeIndex);
            storeIndex++;
        }
        
    }
    //swap right with store index
    swap(storeIndex, right);
    //return store index
    return storeIndex;
}

void swap(int a, int b){
    int temp = nums[a];
    nums[a] = nums[b];
    nums[b] = temp;
}
```

Refer: [Kth Largest Element in an Array](https://leetcode.com/submissions/detail/692620636/)


## Monotonic stack / heap

### Monotonic heap

Usually used for querying max/min in sliding windows

Refer: [Sliding Window Maximum](https://leetcode.com/submissions/detail/239296842/)
Refer: [Max Value of Equation](https://leetcode.com/problems/max-value-of-equation/submissions/)

### Monotonic stack

Search for previous min or next min.

Refer: [Largest Rectangle in Histogram](https://leetcode.com/submissions/detail/237169540/)

Refer: [Maximal Rectangle](https://leetcode.com/submissions/detail/314810956/)

    Make use of Largest Rectange in Histogram

Refer: [Sum of Subarray Minimums](https://leetcode.com/submissions/detail/696897687/)