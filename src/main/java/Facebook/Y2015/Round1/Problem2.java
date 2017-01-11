package Facebook.Y2015.Round1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

//start at 0:37, ac on 0:59

class Node implements Comparable<Node>{
    public char a;
    public TreeMap<Character, Node> nodeList ;
    int cnt;

    public Node(char a){
        cnt = 1;
        this.a = a;
        nodeList  = new TreeMap<>();
    }

    @Override
    public int compareTo(Node o) {
        return a - o.a;
    }
}

public class Problem2 {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            FILENAME = "C:\\Users\\Tom\\Documents\\algorithm_practice\\autocomplete (1)";
            IN = FILENAME + ".txt";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void addWord(Node root, String w){
        Node cur = root;
        root.cnt += 1;
        for(int i=0; i<w.length(); ++i){
            Node n = cur.nodeList.get(w.charAt(i));
            if(n == null) {
                n = new Node(w.charAt(i));
                cur.nodeList.put(w.charAt(i), n);
            }
            else
                n.cnt += 1;
            cur = n;
        }
    }

    private int searchWord(Node root, String w){
        Node cur = root;
        int i;
        for(i=0; i<w.length(); ++i){
            Node n = cur.nodeList.get(w.charAt(i));
            if(n.cnt == 1)
                break;
            cur = n;
        }

        return Math.min(i+1, w.length());
    }

    private void solve(int n, String[] w) {
        Node root = new Node(' ');
        TreeSet<String> dict = new TreeSet<>();

        int result = 0;
        for(int i=0; i<n; ++i){
            if(!dict.contains(w[i]))
                addWord(root, w[i]);

            int t = searchWord(root, w[i]);
            //System.out.println(w[i].substring(0, t));
            result += t;
        }

        out.println(result);
        System.out.println(result);
    }

    String[] test = new String[100000];

    private void run() throws Exception {

        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            System.out.print("Case #" + i + ": ");
            out.print("Case #" + i + ": ");

            int n = sc.nextInt();
            sc.nextLine();

            for(int j=0; j<n; ++j){
                test[j] = sc.nextLine();
            }

            solve(n, test);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Problem2().run();
    }
}
