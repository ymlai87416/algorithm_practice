package GoogleCodeJam.Y2009.Round1B.A;

/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            //IN = "C:\\Users\\ITDLYG\\IdeaProjects\\untitled\\B-test.in";
            IN = null;
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

    static class Node{
        Node nTrue;
        Node nFalse;
        double value;
        String property;
    }

    private double[] solve(String tree, String[] query) {

        Node root = parseTree(tree);

        double[] ans = new double[query.length];
        for (int i = 0; i < query.length; i++) {
            double r = 1;
            Node ptr = root;
            String[] token = query[i].split(" ");

            String animal = token[0];
            int nProperty = Integer.parseInt(token[1]);
            HashSet<String> propSet = new HashSet<>();

            for (int j = 0; j < nProperty; j++)
                propSet.add(token[2+j]);

            while(ptr != null){
                r = r * ptr.value;


                if(ptr.property != null){
                    boolean hasProp = propSet.contains(ptr.property);
                    if(hasProp)
                        ptr = ptr.nTrue;
                    else
                        ptr = ptr.nFalse;
                }
                else break;

            }


            ans[i] =  r;
        }

        return ans;
    }

    private Node parseTree(String s){
        //System.out.println("f " + s);
        //find root, //find left //find right
        int treeStart = -1, treeEnd = -1, leftTreeStart = -1, leftTreeEnd = -1, rightTreeStart= -1, rightTreeEnd =-1;
        int state = 0;

        Node n = new Node();
        String value = "";
        String description = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '('){
                treeStart = i;
                break;
            }
        }

        int cStart = treeStart+1, cEnd = -1;
        for (int i = cStart; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') {
                cEnd = i;
                String content = s.substring(cStart, cEnd);
                //System.out.println("Dx " + content);
                String[] token = content.trim().split(" ");
                n.value = Double.parseDouble(token[0].trim() );
                n.property = token[1].trim();
                leftTreeStart = i;
                break;

            } else if(c == ')'){
                cEnd = i;
                String content = s.substring(cStart, cEnd);
                //System.out.println("Dx " + content);
                String[] token = content.trim().split(" ");
                n.value = Double.parseDouble(token[0].trim() );
                return n;
            }
        }

        //find left tree,
        int b = 1;
        for (int i = leftTreeStart+1; i < s.length(); i++) {
            char c =s.charAt(i);
            if( c == '(') b+=1;
            else if(c == ')') b-=1;

            if(b == 0){
                leftTreeEnd = i;
                break;
            }
        }

        for (int i = leftTreeEnd+1; i < s.length(); i++) {
            char c = s.charAt(i);
            if( c=='(') {
                rightTreeStart = i;
                break;
            }
        }

        b=1;
        for (int i = rightTreeStart+1; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') b+=1; else if(c == ')') b-=1;
            if(b == 0){
                rightTreeEnd = i;
                break;
            }
        }

        String leftTreeStr = s.substring(leftTreeStart, leftTreeEnd+1);
        String rightTreeStr = s.substring(rightTreeStart, rightTreeEnd+1);

        //System.out.println("D " + leftTreeStr);
        //System.out.println("D " + rightTreeStr);

        n.nTrue = parseTree(leftTreeStr);
        n.nFalse = parseTree(rightTreeStr);

        return n;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ":");
            int L = sc.nextInt();

            sc.nextLine();
            String a = "";
            for (int j = 0; j < L; j++) {
                a = a + ' ' + sc.nextLine();
            }

            int A = sc.nextInt();
            sc.nextLine();
            String[] query = new String[A];
            for (int j = 0; j < A; j++) {
                query[j] = sc.nextLine();
            }

            double[] x = solve(a, query);
            System.out.println();
            for (int j = 0; j < x.length; j++) {
                System.out.format("%.9f\n", x[j]);
            }
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}