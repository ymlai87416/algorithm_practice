package Facebook.Y2015.Round1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//start at 0:37, ac on 0:59



public class Problem2MT {
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

    static class Node implements Comparable<Node>{
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

    private int solve(int n, String[] w) {
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

        return result;
    }



    private void run() throws Exception {

        int t = sc.nextInt();
        sc.nextLine();

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        Future<Integer>[] fs = new Future[t];
        for(int i=0; i<t; ++i){
            int n = sc.nextInt();
            sc.nextLine();

            String[] test = new String[n];
            for(int j=0; j<n; ++j){
                test[j] = sc.nextLine();
            }

            fs[i] = executorService.submit(() -> solve(n, test));
        }

        for (int i = 1; i <= t; i++) {
            System.out.print("Case #" + i + ": ");
            out.print("Case #" + i + ": ");


            int result = fs[i-1].get();
            out.println(result);
            System.out.println(result);
        }
        sc.close();
        out.close();

        executorService.shutdown();
    }

    public static void main(String args[]) throws Exception {
        long start_time = System.currentTimeMillis();
        new Problem2MT().run();
        long end_time = System.currentTimeMillis();
        long execution_time = (end_time - start_time);

        System.out.println(String.format("Total runtime: %dms", execution_time));
    }
}
