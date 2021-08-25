package Facebook.Y2020.Quali;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

//first tree put right only if it touch the root / next tree put left and touch the tips
/*
    LRL, formula a recursive first and then see see
    //fuck.
 */


public class C {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2020\\Quali\\timber_input.txt";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            out = new PrintStream(System.out);
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

    int N;
    //int[] P = new int[800000];
    //int[] H = new int[800000];
    Tree[] tree = new Tree[800000];

    HashMap<Integer, List> treeRoot = new HashMap<>();
    HashMap<Integer, List> leftFail = new HashMap<>();

    int[] dp = new int[800000*2];

    private int findMax(int i, char p){
        if(i == N) return 0;
        //System.out.println(i);
        int dpi = i*2 + (p=='L' ? 0 : 1);
        if(dp[dpi] != -1) return dp[dpi];

        int e = tree[i].P + p=='L' ? -tree[i].H : tree[i].H;
        if(p == 'L') {
            List<Integer> t = leftFail.get(tree[i].P);

            dp[dpi] = tree[i].H;
            if(t!= null) {
                for (int j = 0; j < t.size(); j++) {
                    dp[dpi] = Math.max(dp[dpi], tree[i].H + findMax(t.get(j), 'L'));
                }
            }
        }
        else if(p == 'R') {

            dp[dpi] = tree[i].H;

            List<Integer> t = treeRoot.get(tree[i].P+tree[i].H);

            if(t!=null) {
                for (int j = 0; j < t.size(); j++) {
                    dp[dpi] = Math.max(dp[dpi], tree[i].H + findMax(t.get(j), 'R'));
                }
            }


            t = leftFail.get(tree[i].P+tree[i].H);
            if(t !=null) {
                for (int j = 0; j < t.size(); j++) {
                    dp[dpi] = Math.max(dp[dpi], tree[i].H + findMax(t.get(j), 'L'));
                }
            }

        }


        return dp[dpi];
    }

    private int solve() {
        int ans = 0;

        for (int i = 0; i < N; i++) {
            dp[i*2] = -1;
            dp[i*2+1] = -1;
        }

        treeRoot.clear();
        leftFail.clear();
        for(int i=0; i<N; ++i){
            if(treeRoot.containsKey(tree[i].P)) treeRoot.get(tree[i].P).add(i);
            else{
                ArrayList<Integer> na = new ArrayList<>(); na.add(i);
                treeRoot.put(tree[i].P, na);
            }

            if(leftFail.containsKey(tree[i].P-tree[i].H)) leftFail.get(tree[i].P-tree[i].H).add(i);
            else{
                ArrayList<Integer> na = new ArrayList<>(); na.add(i);
                leftFail.put(tree[i].P-tree[i].H, na);
            }
        }

        int maxR = 0;
        for(int i=N*2-1; i>=0; --i)
            if(dp[i] == -1) {
                findMax(i/2, i % 2 == 0 ? 'L' : 'R');
                maxR = Math.max(dp[i], maxR);
            }

        return maxR;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            N = sc.nextInt();
            debug(N);
            for (int j = 0; j < N; j++) {
                tree[j] = new Tree(sc.nextInt(), sc.nextInt());
                //P[j] = sc.nextInt();
                //H[j] = sc.nextInt();
            }

            Arrays.sort(tree, 0, N);
            int r = solve();
            System.out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new C().run();
    }

    //class and sort
}

class Tree implements Comparable<Tree>{
    public int P;
    public int H;
    public Tree(int P, int H){this.P = P; this.H = H;}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Tree))
            return false;
        if (obj == this)
            return true;
        return (this.P == ((Tree) obj).P && this.H == ((Tree) obj).H);
    }

    @Override
    public int hashCode() {
        return P*36709+H;
    }


    @Override
    public int compareTo(Tree o) {
        if(P == o.P) return H - o.H;
        return P-o.P;
    }
}