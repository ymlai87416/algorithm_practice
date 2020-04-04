package GoogleCodeJam.Y2014.Round1B; /**
 * Created by Tom on 9/4/2016.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class A {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            FILENAME = "A-large-practice (9)";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false)); ;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    static class Node{
        char c;
        int freq;
        public Node(char c, int freq){ this.c = c; this.freq = freq;}
    }

    private List<Node> toNodeRep(String a){
        char c = 0; int freq = 1;
        List<Node> aNode = new ArrayList<Node>();
        for(int i=0; i<a.length(); ++i){
            c = a.charAt(i); freq = 1;
            while(true) {
                if(i+1 == a.length()){
                    aNode.add(new Node(c, freq));
                    break;
                }
                else if (a.charAt(i + 1) == c) {
                    freq += 1;
                    i++;
                } else {
                    aNode.add(new Node(c, freq));
                    break;
                }
            }
        }

        return aNode;
    }


    private void solve(List<String> arr) {
        int ans = 0;

        List<List<Node>> nodeList = new ArrayList<>();
        for(int i=0; i<arr.size(); ++i){
            nodeList.add(toNodeRep(arr.get(i)));
        }

        for(int i=0; i<nodeList.size(); ++i){
            for(int j=1; j<nodeList.get(i).size(); ++j){
                Node n = nodeList.get(i).get(j);
                System.out.print(n.c + " " + n.freq + " ");
            }
            System.out.println();
        }

        boolean canDo = true;
        int nNode = nodeList.get(0).size();
        for(int i=1; i<nodeList.size(); ++i)
            if(nNode != nodeList.get(i).size()) {
                canDo = false;
                break;
            }

        if(canDo)
            for(int i=0; i<nNode; ++i){
                char c = nodeList.get(0).get(i).c;
                for(int j=1; j<nodeList.size(); ++j){
                    if(nodeList.get(j).get(i).c != c) {
                        canDo = false;
                        break;
                    }
                }
            }

        //median is the smallest
        if(canDo){
            ans = 0;
            List<Integer> freqList = new ArrayList<>();
            for(int i=0; i<nNode; ++i){
                int upper;
                int lower;

                freqList.clear();
                for(int j=0; j<nodeList.size(); ++j){
                    freqList.add(nodeList.get(j).get(i).freq);
                }
                Collections.sort(freqList);
                if(freqList.size() %2 == 0){
                    upper = freqList.get(freqList.size()/2);
                    lower = freqList.get(freqList.size()/2-1);
                }
                else
                    upper = lower = freqList.get(freqList.size()/2);

                int distU = 0;
                int distL = 0;

                for(int j=0; j<nodeList.size(); ++j){
                    distU += Math.abs(upper- nodeList.get(j).get(i).freq);
                    distL += Math.abs(lower- nodeList.get(j).get(i).freq);
                }

                if(distU < distL)
                    ans +=distU;
                else
                    ans +=distL;
            }
            out.println(ans);
        }
        else
            out.println("Fegla Won");
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int n = sc.nextInt();
            List<String> arr = new ArrayList<String>();
            for(int j=0; j<n; ++j){
                arr.add(sc.next());
            }
            solve(arr);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new A().run();
    }

}