# String

## Regular expression

```java

```


## KMP searching

Time complexity: O(m+n)

Can use to detect longest parlindrom from the beginning

```java
final int MAX_N = 1_000_001;
char[] T = new char[MAX_N];
char[] P = new char[MAX_N]; // T = text, P = pattern
int[] b = new int[MAX_N];
int n, m; // b = back table, n = length of T, m = length of P

void kmpPreprocess() { // call this before calling kmpSearch()
    int i = 0, j = -1;
    b[0] = -1; // starting values
    while (i < m) { // pre-process the pattern string P
        while (j >= 0 && P[i] != P[j]) j = b[j]; // different, reset j using b
        i++;
        j++; // if same, advance both pointers
        b[i] = j; // observe i = 8, 9, 10, 11, 12, 13 with j = 0, 1, 2, 3, 4, 5
    }
} // in the example of P = "SEVENTY SEVEN" above

void kmpSearch() { // this is similar as kmpPreprocess(), but on string T
    int i = 0, j = 0; // starting values
    while (i < n) { // search through string T
        while (j >= 0 && T[i] != P[j]) j = b[j]; // different, reset j using b
        i++;
        j++; // if same, advance both pointers
        if (j == m) { // a match found when j == m
            System.out.format("%s is found at index %d in T\n", String.valueOf(P, 0, m), i - j);
            j = b[j]; // prepare j for the next possible match
        }
    }
}
```

Refer: [UVA1449](https://github.com/ymlai87416/algorithm_practice/blob/e1081a55e42fbde08c0514e60008d96770942531/java/src/main/java/StringProcessing/StringMatchingStandard/UVA1449.java)

## Wildcard matching

Time complexity: O(S+P)
Similar to KMP matching, just go back to the last star position and match again, but now star match 1 more character.
Better than DP.

Refer: [Wildcard Matching](https://leetcode.com/submissions/detail/230018120/)

## Minimum edit dista

Operation include: insert, delete, replace

Time complexity: O(m*n)

```java
public int minDistance(String word1, String word2) {
    int[][] dp = new int[word1.length()+1][word2.length()+1];

    dp[0][0] = 0;
    for(int i=0; i<word1.length(); ++i)
        dp[i+1][0] = i+1; //delete char
    for(int i=0; i<word2.length(); ++i)
        dp[0][i+1] = i+1; //delete char

    for(int i=1; i<=word1.length(); ++i){
        for(int j=1; j<=word2.length(); ++j){
            char ci = word1.charAt(i-1);
            char cj = word2.charAt(j-1);

            if(ci==cj)
                dp[i][j] = dp[i-1][j-1];
            else
                dp[i][j] = dp[i-1][j-1]+1;

            dp[i][j] = Math.min(dp[i][j], Math.min(dp[i-1][j]+1, dp[i][j-1]+1));
        }
    }

    return dp[word1.length()][word2.length()];
}
```

Refer: [Edit Distance](https://leetcode.com/submissions/detail/234447813/)

## Longest common substring

## Longest palindrom

From starting
In between

## Suffix trie

```java

```

## Suffix tree
- string matching O(m + occ)
- longest repeating substring O(n)
```java

```

## Suffix array

Construction of SA

Time complexity: O(n log(n))
Time complexity of sorting string array using strcmp is O(n^2 log(n))

```java
int MAX_N = 100_010; // second approach: O(n log n)
char[] T = new char[MAX_N]; // the input string, up to 100K characters
int n; // the length of input string
int[] RA = new int[MAX_N], tempRA = new int[MAX_N]; // rank array and temporary rank array
int[] SA = new int [MAX_N], tempSA = new int[MAX_N]; // suffix array and temporary suffix array
int[] c = new int[MAX_N];

void countingSort(int k) { // O(n)
    int i, sum, maxi = Math.max(300, n); // up to 255 ASCII chars or length of n
    Arrays.fill(c, 0); // clear frequency table
    for (i = 0; i < n; i++) // count the frequency of each integer rank
        c[i + k < n ? RA[i + k] : 0]++;
    for (i = sum = 0; i < maxi; i++) {
        int t = c[i]; c[i] = sum; sum += t; }
    for (i = 0; i < n; i++) // shuffle the suffix array if necessary
        tempSA[c[SA[i]+k < n ? RA[SA[i]+k] : 0]++] = SA[i];
    for (i = 0; i < n; i++) // update the suffix array SA
        SA[i] = tempSA[i];
}

void constructSA() { // this version can go up to 100000 characters
    int i, k, r;
    for (i = 0; i < n; i++) RA[i] = T[i]; // initial rankings
    for (i = 0; i < n; i++) SA[i] = i; // initial SA: {0, 1, 2, ..., n-1}
    for (k = 1; k < n; k <<= 1) { // repeat sorting process log n times
        countingSort(k); // actually radix sort: sort based on the second item
        countingSort(0); // then (stable) sort based on the first item
        tempRA[SA[0]] = r = 0; // re-ranking; start from rank r = 0
        for (i = 1; i < n; i++) // compare adjacent suffixes
            tempRA[SA[i]] = // if same pair => same rank r; otherwise, increase r
                    (RA[SA[i]] == RA[SA[i-1]] && RA[SA[i]+k] == RA[SA[i-1]+k]) ? r : ++r;
        for (i = 0; i < n; i++) // update the rank array RA
            RA[i] = tempRA[i];
        if (RA[SA[n-1]] == n-1) break; // nice optimization trick
    }
}

//in main
n = input.length();
Arrays.fill(T, '\0');
for(int i=0; i<_n; ++i){
    T[i] = input.charAt(i);
}
T[n++] = '$';

constructSA();
```

- string matching O(m log n) [UVA1254](https://github.com/ymlai87416/algorithm_practice/blob/e8b0de3670b3a19f3b5f7b98491c5e7ea0dbd31f/java/src/main/java/StringProcessing/SuffixArray/UVA1254.java)

```java
//return (-1, -1) if not found
Pair stringMatching(String P) { // string matching in O(m log n)
    int m = P.length();

    int lo = 0, hi = n-1, mid = lo; // valid matching = [0..n-1]
    while (lo < hi) { // find lower bound
        mid = (lo + hi) / 2; // this is round down
        int res = strncmp(T, SA[mid], P, m); // try to find P in suffix ’mid’
        if (res >= 0) hi = mid; // prune upper half (notice the >= sign)
        else lo = mid + 1; // prune lower half including mid
    } // observe ‘=’ in "res >= 0" above
    if (strncmp(T, SA[lo], P, m) != 0) return new Pair(-1, -1); // if not found
    Pair ans = new Pair(0, 0); ans.first = lo;
    lo = 0; hi = n - 1; mid = lo;
    while (lo < hi) { // if lower bound is found, find upper bound
        mid = (lo + hi) / 2;
        int res = strncmp(T, SA[mid], P, m);
        if (res > 0) hi = mid; // prune upper half
        else lo = mid + 1; // prune lower half including mid
    } // (notice the selected branch when res == 0)
    if (strncmp(T, SA[hi], P, m) != 0) hi--; // special case
    ans.second = hi;
    return ans;
} // return lower/upperbound as first/second item of the pair, respectively

int strncmp(char[] str1, int startPos, String P, int num ){
    for(int i=0; i<num; ++i){
        char a = str1[startPos+i];
        char b = P.charAt(i);
        if(a != b) return (int)(a-b);
    }
    return 0;
}
```

- longest prefix O(n)
```java
int[] Phi= new int[MAX_N];
int[] PLCP= new int[MAX_N];
int[] LCP = new int[MAX_N];
void computeLCP() {
    int i, L;
    Phi[SA[0]] = -1; // default value
    for (i = 1; i < n; i++) // compute Phi in O(n)
        Phi[SA[i]] = SA[i-1]; // remember which suffix is behind this suffix
    for (i = L = 0; i < n; i++) { // compute Permuted LCP in O(n)
        if (Phi[i] == -1) { PLCP[i] = 0; continue; } // special case
        while (T[i + L] == T[Phi[i] + L]) L++; // L increased max n times
        PLCP[i] = L;
        L = Math.max(L-1, 0); // L decreased max n times
    }
    for (i = 0; i < n; i++) // compute LCP in O(n)
    LCP[i] = PLCP[SA[i]]; // put the permuted LCP to the correct position
}
```

- Longest common substring
Time complexity: O(n)

for T1= GATAGACA and T2=CATA, concat both string to become GATAGACA$CATA#.

Go through the suffix array to see if 2 consecutive suffix belongs to 2 different word.


Refer: []()

- Longest repeating substring 
Time complexity: O(n) 

Refer: [Longest Duplicate substring](https://leetcode.com/submissions/detail/689901108/)


## Rolling hash - Rabin Karp

Time complexity: O(m+n)
Space complexity: O(n)

Time complexity is same as KMP.

```java

int modulo = 1_000_000_007;
    
private long modPow(int base, int exp){
    if(exp == 0) return 1;
    else if(exp == 1) return base;
    
    long d = modPow(base, exp/2);
    
    if(exp % 2 == 0)
        return (d * d) % modulo;
    else
        return ((d * d) % modulo * base) % modulo;
}

//in main
long p26 = modPow(26, len-1);

//in loop
int c = s.charAt(i) - 'a';
int q = s.charAt(i-len) - 'a';
rolling = (rolling - (q * p26) % modulo + modulo) % modulo;
rolling = (rolling * 26 + c) % modulo;
```

Refer: [Longest Duplicate Substring](https://leetcode.com/submissions/detail/696974797/)

## Aho corasick

It can match a dictionary to a string simultaneously.

```java
```

Refer: [](https://leetcode.com/problems/stream-of-characters/)