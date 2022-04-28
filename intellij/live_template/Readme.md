# A list of intellij live template to help me


## To install

Create a new template in Intellij and copy the whole content to the directory 

```The code is store at location on mac: /Users/xxx/Library/Application Support/JetBrains/IntelliJIdea2022.1/templates/algorithm.xml```

# Topic include

This will split

- Data structure
    - union find
    - segment tree
        - rmq
        - lazy
    - fenwick tree
        - rmq
        
- dp
    - tsp

- Graph
    - bfs O(V + E)
    - dfs
    - connected component
    - flood fill
    - topological sort dfs
    - topological sort bfs
    - bipartite graph check
    - articulation point and bridges
    - strongly connected graph
    - minimium spanning tree - kruskal
    - minimium spanning tree - prim
    - shortest path - unweighted - bfs
    - dijkstra O((V +E) log V )
    - bellman ford O(VE)
    - floyd O(V^3)
        scc 
        radius directed graph
    - adjlist
    - adjmatrix
    - minmax and maxmin?
    - Ford Fulkerson’s O(f*(E))
    - Edmonds Karp’s O(VE^2)
    - Dinic O(V^2E)
        faster if many edges
    - DAG
        - longest/shortest path
        - counting path
    - minimum vertex cover tree
    - tree
        - pre-order (it/recursive)
        - in-order (it/recursive)
        - post-order (it/recursive)
    - euler graph
        - path
        - cycle
    - bipartite graph
        - max matching
        - min cost max flow
        - max cardinality bipartite matching => vertex in min vertex cover
    - meet in the middle
    - A star
    - iterative deepening A star
    - chinese postman

- maths
    - nCr and nPr
    - prime testing
    - fibonacci
    - binomial 
    - catalan
        - distinct binary tree of node N
        - way to parathesis
        - way to triangluarize convex polygon
        - monotonic path in square grid
    - prime seive
        - check prime
        - count different prime
    - gcd
    - factorial
    - prime factor
        - num of prime
        - num diff prime factor
        - sum of prime factor
        - num divisor
        - sum of divisor
        - euler phi - num of coprime

    - modular
        - helper function +, -, *, / 
        - linear diophantine equation (e.g 25x + 18y = 839)

    - probability
        - close
        - search

    - cycle finding μ = start, λ = cycle
        - store in map or array
        - floyd O(μ + λ)
    - game theroy
        - minmax
        - nim game
    - matrix multiplication

- binary
    - no of bit set

- string
    - formatting
        - fix len whatever radix?

    - string
        - kmp algorithm
        - edit distance
        - longest common substring
        - longest palindrom
            from begin
            from end
            whatever

    - suffix trie
        
    - suffix tree (more compact)
        - string matching O(m)
        - longest repeating substring O(n)
    - suffix array
        - string matching O(m log n)
        - longest prefix O(n)
        - longest common substring O(n)

- geometry
    - point
    - line
    - circle
    - triangle
        - in circle
        - circumcircle
    - polygon
        - is convex
        - in polygon
        - cut polygon
        - convex hull

- adhoc
    - 2 SAT
    - horn-satisfiability 
    - art gallery
    - bitonic TSP
    - closet pair

## Reference

[1] Competitive programming 3