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