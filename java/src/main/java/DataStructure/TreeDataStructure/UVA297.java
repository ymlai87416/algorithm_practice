package DataStructure.TreeDataStructure;

/**
 url: https://onlinejudge.org/external/2/297.pdf
 level:
 solution:

 #quadtree #tree

 **/

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class UVA297 {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\DataStructure\\TreeDataStructure\\UVA297.in";
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

    private int solve(String tree, String tree2) {
        curptr=0;
        Node root1 = buildTree(tree);
        curptr=0;
        Node root2 = buildTree(tree2);
        Node f = add(root1, root2);
        return numBlackPixel(f, 32);
    }

    static class Node{
        char c;
        Node[] subNode = new Node[4];

        public Node(char c){
            this.c = c;
        }
    }

    int curptr;
    private Node buildTree(String a){
        if(a.charAt(curptr) == 'p'){
            Node root = new Node('p');
            curptr++;
            for (int i = 0; i < 4; i++) {
                char pc = a.charAt(curptr++);
                if(pc == 'e') root.subNode[i] = new Node('e');
                else if(pc == 'f') root.subNode[i] = new Node('f');
                else if(pc == 'p'){
                    curptr--;
                    root.subNode[i] = buildTree(a);
                }
            }
            return root;
        }
        else return new Node(a.charAt(curptr));
    }

    private Node add(Node l, Node r){
        if(l.c == 'f' || r.c == 'f')
            return new Node('f');
        else if(l.c == 'e')
            return copy(r);
        else if(r.c == 'e')
            return copy(l);
        else{ //both are p
            Node newNode = new Node('p');
            for (int i = 0; i < 4; i++) {
                newNode.subNode[i] = add(l.subNode[i], r.subNode[i]);
            }
            return newNode;
        }
    }

    private Node copy(Node a){
        Node newNode = new Node(a.c);
        if(a.c == 'p'){
            for (int i = 0; i < 4; i++) {
                newNode.subNode[i] = copy(a.subNode[i]);
            }
        }

        return newNode;
    }

    private int numBlackPixel(Node r, int len){
        if(r.c == 'p'){
            int rr = 0;
            for (int i = 0; i < 4; i++) {
                rr += numBlackPixel(r.subNode[i], len/2);
            }
            return rr;
        }
        else if(r.c == 'f') return len*len; else return 0;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            //out.print("Case #" + i + ": ");
            String t1 = sc.nextLine();
            String t2 =sc.nextLine();
            out.format("There are %d black pixels.\n", solve(t1, t2));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new UVA297().run();
    }

}
