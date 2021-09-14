package Facebook.Y2021.Quali;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class C {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            //IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Quali\\C1-test.in";
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Quali\\gold_mine_chapter_1_input.txt";
            OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Quali\\gold_mine_chapter_1_output.txt";
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


    private long dfs(Node root){
        //return the max
        long mm = 0;
        for (int i = 0; i < root.next.size(); i++) {
            long bsum = dfs(root.next.get(i));
            root.childSum.add(bsum);

            if(bsum > mm)
                mm = bsum;
        }

        return root.val + mm;
    }

    private long solve(Node root) {

        dfs(root);

        if(root.next.size() == 0) return root.val;
        else if(root.childSum.size() == 1) return root.childSum.get(0) + root.val;
        else {
            Collections.sort(root.childSum);
            int len = root.childSum.size();
            return root.childSum.get(len-2) + root.childSum.get(len-1) + root.val;
        }
    }

    private long solve2(Node root){
        //the algorithm is simple just build 50x50 grid and also a list of 49 to (i, j) pair
        //the use priority queue to finish the job

        return 0;
    }

    private void buildTree(int N, Node[] ns, int ri, int[] u, int[] v){
        Node n = ns[ri];
        for(int i=0; i<N-1; ++i){
            if(u[i] == ri) {
                n.next.add(ns[v[i]]);
                u[i] = v[i] = -1;
            }
            else if (v[i] == ri) {
                n.next.add(ns[u[i]]);
                u[i] = v[i] = -1;
            }
        }

        //now we build the next level tree
        for (int i = 0; i < n.next.size(); i++) {
            buildTree(N, ns, n.next.get(i).idx, u, v);
        }
    }

    private void removeParentEdge(Node n){
        for (int i = 0; i < n.next.size(); i++) {
            Node nn = n.next.get(i);
            nn.next.remove(n);
        }
        for (int i = 0; i < n.next.size(); i++) {
            removeParentEdge(n.next.get(i));
        }
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        //sc.nextLine();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();

            Node[] ns= new Node[N];
            for (int j = 0; j < N; j++) {
                ns[j] = new Node(j, sc.nextInt());
            }

            for (int j = 0; j < N-1; j++) {
                int u= sc.nextInt()-1;
                int v= sc.nextInt()-1;

                ns[u].next.add(ns[v]);
                ns[v].next.add(ns[u]);
            }

            removeParentEdge(ns[0]);

            long r = solve(ns[0]);

            out.format("%d\n", r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new C().run();
    }
}

class Node{
    public int idx;
    public int val;
    public ArrayList<Node> next;
    public ArrayList<Long> childSum;
    public Node(int idx, int v){
        this.idx = idx;
        val = v;
        next = new ArrayList<Node>();
        childSum = new ArrayList<Long>();
    }
}
