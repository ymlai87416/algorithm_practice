package Facebook.Y2019.Round2;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

public class B {
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2019\\Round2\\bitstrings_as_a_service_input.txt";
            OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2019\\Round2\\bitstrings_as_a_service_output.txt";
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



    private String solve(int N, int M, int[][] c) {
        UnionFind uf = new UnionFind(N);

        for (int i = 0; i < M; i++) {

            for (int j = c[i][0], k = c[i][1]; j<k ; j++, --k) {
                uf.unionSet(j-1, k-1);
            }
        }

        //now we have a list of subset sum
        ArrayList<Integer>[] sizes = uf.getAllSetSize();
        int r = 0;

        r = findMin(sizes[1], sizes[1].size());

        debugflag =true;
        debug("diff " + r);

        String sr = backTrack(sizes, r, uf, N);

        return sr;
    }

    // Create an array to store
    // results of subproblems
    boolean dp[][] = new boolean[4001][4001];

    // Returns the minimum value of
    // the difference of the two sets.
    int findMin(ArrayList<Integer> arr, int n)
    {
        // Calculate sum of all elements
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += arr.get(i);

        // Initialize first column as true.
        // 0 sum is possible  with all elements.
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        // Initialize top row, except dp[0][0],
        // as false. With 0 elements, no other
        // sum except 0 is possible
        for (int i = 1; i <= sum; i++)
            dp[0][i] = false;

        // Fill the partition table
        // in bottom up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                // If i'th element is excluded
                dp[i][j] = dp[i - 1][j];

                // If i'th element is included
                if (arr.get(i - 1) <= j)
                    dp[i][j] |= dp[i - 1][j - arr.get(i - 1)];
            }
        }

        // Initialize difference of two sums.
        int diff = Integer.MAX_VALUE;


        // Find the largest j such that dp[n][j]
        // is true where j loops from sum/2 t0 0
        for (int j = sum / 2; j >= 0; j--) {
            // Find the
            if (dp[n][j] == true) {
                diff = sum - 2 * j;
                break;
            }
        }
        return diff;
    }

    String backTrack(ArrayList<Integer>[] sizes,int diff, UnionFind uf, int N){

        boolean[] assign = new boolean[sizes[0].size()];
        //add a hashset
        TreeMap<Integer, Integer> lookup = new TreeMap<>();
        for (int i = 0; i < sizes[0].size(); i++) {
            lookup.put(sizes[0].get(i), i);
        }

        int sum = 0;
        for (int i = 0; i <sizes[1].size(); i++) {
            sum += sizes[1].get(i);
        }

        int subsetSum = (sum-diff)/2;

        int cn = sizes[1].size();
        while(cn > 0) {
            int cs = sizes[1].get(cn-1);

            if (subsetSum - cs >= 0 && dp[cn - 1][subsetSum - cs]){
                assign[cn-1] =true;
                subsetSum -= cs;
            }
            else{
                assign[cn-1] =false;
            }
            --cn;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <N; i++) {
            int x = uf.findSet(i);
            if(assign[lookup.get(x)])
                sb.append('1');
            else
                sb.append('0');
        }

        return sb.toString();
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[][] c = new int[M][2];
            for (int j = 0; j < M; j++) {
                c[j][0] = sc.nextInt();
                c[j][1] = sc.nextInt();
            }

            String r = solve(N, M, c);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new B().run();
    }

    static class UnionFind { // OOP style
        int[] p;
        int[] rank;
        int[] size;
        int N;

        UnionFind(int N) {
            this.N = N;
            p = new int[N];
            rank = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++){
                p[i] = i;
                size[i] = 1;
            }
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

        public ArrayList<Integer>[] getAllSetSize(){

            ArrayList<Integer>[] result = new ArrayList[2];
            result[0] = new ArrayList<Integer>();
            result[1] = new ArrayList<Integer>();
            for (int i = 0; i < N; i++) {
                if(p[i] == i){
                    result[0].add(i);
                    result[1].add(size[i]);
                }
            }
            return result;
        }

        int getSetSize(int i){
            return size[i];
        }
    };
}
