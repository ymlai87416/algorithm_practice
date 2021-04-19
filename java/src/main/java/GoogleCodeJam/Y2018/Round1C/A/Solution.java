package GoogleCodeJam.Y2018.Round1C.A;

/**
 * Created by Tom on 9/4/2016.
 */

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2018\\Round1C\\A\\A-test.in";
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

    static
    class Trie {
        public Node root;
        /** Initialize your data structure here. */
        public Trie() {
            root = new Node(' ');
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Node t = root;
            for(int i=0; i<word.length(); ++i){
                char c = word.charAt(i);
                if(t.next[c-'a'] == null){
                    t.next[c-'a'] = new Node(c);
                }
                t = t.next[c-'a'];
            }
            t.isTerminate = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node t = root;

            for(int i=0; i<word.length(); ++i){
                char c = word.charAt(i);
                t = t.next[c-'a'];
                if(t == null) return false;
            }

            return t.isTerminate;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Node t = root;

            for(int i=0; i<prefix.length(); ++i){
                char c = prefix.charAt(i);
                t = t.next[c-'a'];
                if(t == null) return false;
            }

            return true;
        }
    }

    static
    class Node{
        Node[] next = new Node[26];
        char val;
        boolean isTerminate;

        public Node(char v){
            this.val = v;
            this.isTerminate = false;
        }
    }
    

    Set<Character>[] clist = new Set[11];
    int L;

    private String solve(int N, int L, String[] w) {
        int ans = 0;
        this.L = L;
        //build a tire and then also get a set of word
        //then try to loop through the tire and find the answer

        for (int i = 0; i< clist.length; i++) {
            clist[i] = new TreeSet<>();
        }

        Trie t = new Trie();
        for (int i = 0; i < N; i++) {
            t.insert(w[i]);
            for (int j = 0; j < w[i].length(); j++) {
                clist[j].add(w[i].charAt(j));
            }
        }


        return helper(t.root, "", 0);
    }

    private String helper(Node root, String a, int level){
        if(level == L){
            if(root != null)
                return null;
            else
                return a;
        }
        if(root  == null){
            //any will ok
            char c = clist[level].stream().findFirst().get();
            return helper(null, a+c, level+1);
        }
        else{
            String ans = null;
            for(Character c : clist[level]){
                int offset = c -'a';
                String tans = helper(root.next[offset], a + c, level +1);
                if(tans != null){
                    ans = tans;
                    break;
                }
            }

            return ans;
        }
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N=sc.nextInt();
            int L =sc.nextInt();
            String[] w = new String[N];
            sc.nextLine();
            for (int j = 0; j < N; j++) {
                w[j] = sc.nextLine().toLowerCase();
            }
            String a = solve(N, L, w);
            if(a == null) out.println("-");
            else out.println(a.toUpperCase());
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}