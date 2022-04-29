package StringProcessing.SuffixArray;

/**
 * Created by Tom on 29/4/2022.
 *
 * AC in 1.51s
 *
 * problem: https://onlinejudge.org/external/12/1254.pdf
 * #UVA #Lv2 #suffix_array #string_matching
 */

import java.io.*;
import java.util.*;
import java.math.*;
import java.util.stream.Collectors;

public class UVA1254 {
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/StringProcessing/SuffixArray/UVA1254.in";
            OUT = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/StringProcessing/SuffixArray/UVA1254.out";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            if(OUT == null)
                out = new PrintStream(System.out);
            else out = new PrintStream(OUT);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    boolean debugflag = false;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    private void debug(Object... s){
        if(debugflag) {
            //System.out.println(s);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length; i++) {
                sb.append(s[i].toString() + " ");
            }
            System.out.println("\033[0;34m" + sb.toString() + "\033[0;30m");
        }
    }

    private List<Integer> query(String in) {
        List<Integer> ans;

        //for each query, do a binary search to find range in suffix array
        Pair range = sa.stringMatching(in);
        if(range.first == -1 && range.second==-1)
            return null;

        ans = st.rmq(range.first, range.second);

        return ans;
    }
    SuffixArray sa = null;
    SegmentTree st = null;

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));

        //do the following, create a large string by combining word, separate with $ and add
        //sort the suffix array
        //create a segment tree according to the suffix word => word, e.g SA[10] = cm$ and the word is acm
        int w = sc.nextInt();
        TreeMap<Integer, Integer> locMap = new TreeMap<>();
        String[] dict = new String[w];
        StringBuilder sb = new StringBuilder();
        int curPos = 0;
        for (int i = 0; i < w; i++) {
            dict[i] = sc.next();
            locMap.put(curPos, i);
            sb.append(dict[i]+"$");
            curPos += dict[i].length()+1;
        }
        String fullText = sb.toString();
        int n = fullText.length();
        sa = new SuffixArray(fullText);
        int[] wordIndex = new int[n];
        for(int i=0; i<n; ++i){
            //now index all the suffix to word index
            int loc = sa.SA[i];
            wordIndex[i] = locMap.get(locMap.floorKey(loc));
        }

        st = new SegmentTree(wordIndex, n, dict);

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            String q = sc.next();
            List<Integer> r = query(q);
            if(r == null)
                out.println("-1");
            else {
                List<String> rs = r.stream().map(x -> x+1).map(String::valueOf).collect(Collectors.toList());
                out.println(String.join(" ", rs));
            }
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new UVA1254().run();
    }

}

class SuffixArray{
    int MAX_N = 150_000; // second approach: O(n log n)
    char[] T = new char[MAX_N]; // the input string, up to 100K characters
    int n; // the length of input string
    int[] RA = new int[MAX_N], tempRA = new int[MAX_N]; // rank array and temporary rank array
    int[] SA = new int [MAX_N], tempSA = new int[MAX_N]; // suffix array and temporary suffix array
    int[] c = new int[MAX_N];

    public SuffixArray(String input){
        for(int i=0; i<input.length(); ++i)
            T[i] = input.charAt(i);

        n = input.length();
        constructSA();
    }

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

    int strncmp(char[] str1, int startPos, String P, int num ){
        for(int i=0; i<num; ++i){
            char a = str1[startPos+i];
            char b = P.charAt(i);
            if(a != b) return (int)(a-b);
        }
        return 0;
    }

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

}

class SegmentTree{
    int MAX_N = 150_000;
    private List[] st = new List[MAX_N*4];
    private int[] A;
    private int n;
    private String[] dict;

    private int left(int p) {
        return p << 1;
    } // same as binary heap operations

    private int right(int p) {
        return (p << 1) + 1;
    }

    private List<Integer> combineTop10(List<Integer> a, List<Integer> b){
        //shorter length
        //lexicographically
        //smaller label come first
        ArrayList<Integer> result = new ArrayList<>();

        int aptr = 0;
        int bptr = 0;

        while(aptr != a.size() || bptr != b.size()){

            if(result.size() >= 10) break;

            if(aptr == a.size())
                addToResult(result, b.get(bptr++));
            else if(bptr == b.size())
                addToResult(result, a.get(aptr++));
            else {
                //now compare
                int ai = a.get(aptr);
                int bi = b.get(bptr);
                String as = dict[ai];
                String bs = dict[bi];

                if(as.length() == bs.length()){
                    if (as.compareTo(bs) == 0) {
                        if (ai < bi) {
                            addToResult(result, ai);
                            aptr++;
                        } else {
                            addToResult(result, bi);
                            bptr++;
                        }
                    } else if (as.compareTo(bs) < 0) {
                        addToResult(result, ai);
                        aptr++;
                    } else {
                        addToResult(result, bi);
                        bptr++;
                    }
                }
                else if(as.length() < bs.length()){
                    addToResult(result, ai);
                    aptr++;
                }
                else{
                    addToResult(result, bi);
                    bptr++;
                }
            }
        }

        return result;
    }

    private void addToResult(List<Integer> result, int num){
        if(result.size() > 0 && result.get(result.size()-1) == num)
            return;
        else result.add(num);
    }

    private void build(int p, int L, int R) { // O(n)
        if (L == R) {// as L == R, either one is fine
            st[p] = new ArrayList<Integer>();
            st[p].add(A[L]);
        }
        else { // recursively compute the values
            build(left(p), L, (L + R) / 2);
            build(right(p), (L + R) / 2 + 1, R);
            List<Integer> p1 = st[left(p)];
            List<Integer> p2 = st[right(p)];
            st[p]= combineTop10(p1, p2);
        }
    }

    private List<Integer> rmq(int p, int L, int R, int i, int j) { // O(log n)
        if (i > R || j < L) return null; // current segment outside query range

        if (L >= i && R <= j) {
            return st[p];
        }
        // compute the min position in the left and right part of the interval
        List<Integer> p1 = rmq(left(p), L, (L + R) / 2, i, j);
        List<Integer> p2 = rmq(right(p), (L + R) / 2 + 1, R, i, j);
        if (p1 == null) return p2; // if we try to access segment outside query
        if (p2 == null) return p1; // same as above
        return combineTop10(p1, p2); // as in build routine
    }

    public SegmentTree(int[] A, int size, String[] dict) {
        this.A = A;
        this.dict = dict;
        n = size;
        build(1, 0, n - 1); // recursive build
    }

    public List<Integer> rmq(int i, int j) {
        return rmq(1, 0, n - 1, i, j);
    }
}

class Pair implements Comparable<Pair> {
    public int first;
    public int second;

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