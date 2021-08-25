package Facebook.Y2019.Quali;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

//WTF, you left out the XOR....
public class C {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2019\\Quali\\mr_x_input.txt";
            OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2019\\Quali\\mr_x_output.txt";
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

    private int solve(String s) {
        int ans = 0;

        Node root = parseTree(s);
        ans = Math.min(dp(root, '0'), dp(root, '1'));

        return ans;
    }

    int calc(Node r, int xval){
        if(r.val == 'x') return xval;
        else if (r.val == 'X') return 1-xval;
        else if (r.val == '0') return 0;
        else if (r.val == '1') return 1;
        else{
            int left = calc(r.left, xval);
            int right = calc(r.right, xval);

            if(r.val == '&') return left==1 && right==1 ? 1: 0;
            if(r.val == '|') return left==1 || right==1 ? 1: 0;
            if(r.val == '^') return left != right ? 1: 0;
        }
        System.out.println("exception");
        return 99999;
    }

    char calc2(Node r) {
        if (r.val == 'x') return 'x';
        else if (r.val == 'X') return 'X';
        else if (r.val == '0') return '0';
        else if (r.val == '1') return '1';
        else if (r.val == '&'){
            char left = calc2(r.left);
            char right = calc2(r.right);
            if(left == '0' || right == '0') return '0';
            if(left == '1') return right;
            if(right =='1') return left;
            if(left == right) return left;
            if(left != right) return '0';
        }
        else if (r.val == '|'){
            char left = calc2(r.left);
            char right = calc2(r.right);
            if(left == '1' || right == '1') return '1';
            if(left == '0') return right;
            if(right =='0') return left;
            if(left == right) return left;
            if(left != right) return '1';
        }
        else if (r.val == '^'){
            char left = calc2(r.left);
            char right = calc2(r.right);
            if((left == '1' && right=='0')|| (right == '1' && left=='0')) return '1';
            if((left == '1' && right=='1')|| (right == '0' && left=='0')) return '0';
            if(left == right && left == 'x') return '0';
            if(left == right && left == 'X') return '0';
            if((left=='x' && right == 'X') || (left=='X' && right=='x')) return '1';
            if(left == '0') return right;
            if(left == '1') return right == 'x' ? 'X' : 'x';
            if(right == '0') return left;
            if(right == '1') return left == 'x' ? 'X' : 'x';
            return '0';
        }
        System.out.println("exception");
        return 'E';
    }

    private HashMap<NodeTarget, Integer> dpc = new HashMap<>();

    //make it more general, for each node, xVal and target (1/0), effort to make it 1 or 0
    //t==2 means can be 1/0,
    private int dp(Node r, char t){
        if(r.val != '&' && r.val != '|'){
            return t == r.val ? 0: 1;
        }

        NodeTarget nt = new NodeTarget(r, t);
        if(dpc.containsKey(nt)){
            return dpc.get(nt);
        }

        int l0 = dp(r.left, '0');
        int r0 = dp(r.right, '0');
        int l1 = dp(r.left, '1');
        int r1 = dp(r.right, '1');
        int lx = dp(r.left, 'x');
        int rx = dp(r.right, 'x');
        int lX = dp(r.left, 'X');
        int rX = dp(r.right, 'X');

        int ans = 999999;
        List<Integer> poss = null;
        if(r.val == '&'){
            //if target is 0, either side can be 0 or x & X
            if(t == '0') {
                int c1 = l0;
                int c2 = r0;
                int c3 = lx + rX;
                int c4 = lX + rx;
                poss = Arrays.asList(c1, c2,c3,c4);
            }

            //if target is 1, both side be 1
            if(t == '1') {
                int c5 = l1 + r1;
                poss = Arrays.asList(c5);
            }

            //if target is x, can be 1&x or x&x
            if(t == 'x') {
                int c6 = l1 + rx;
                int c7 = lx + r1;
                int c8 = lx + rx;
                poss = Arrays.asList(c6,c7,c8);
            }

            //if target is X, can be 1&X or X&X
            if(t == 'X') {
                int c9 = l1 + rX;
                int c10 = lX + r1;
                int c11 = lX + rX;
                poss = Arrays.asList(c9,c10, c11);
            }

            ans = poss.stream().min(Integer::compare).get();
        }

        if(r.val == '|'){

            //if target is 0, both side must be 0
            if(t == '0') {
                int c1 = l0 + r0;
                poss = Arrays.asList(c1);
            }

            //if target is 1, either side can be 1 or x | X
            if(t == '1') {
                int c2 = l1;
                int c3 = r1;
                int c4 = lx + rX;
                int c5 = lX + rx;
                poss = Arrays.asList(c2,c3,c4, c5);
            }

            //if target is x, can be 0|x or x|x
            if(t == 'x') {
                int c6 = l0 + rx;
                int c7 = lx + r0;
                int c8 = lx + rx;
                poss = Arrays.asList(c6, c7,c8);
            }

            //if target is X, can be 0|X or X|X
            if(t == 'X') {
                int c9 = l0 + rX;
                int c10 = lX + r0;
                int c11 = lX + rX;
                poss = Arrays.asList(c9, c10,c11);
            }

            ans = poss.stream().min(Integer::compare).get();
        }

        dpc.put(nt, ans);
        return ans;
    }

    private Node parseTree(String in){
        if(in.charAt(0) == '(' && in.charAt(in.length()-1) == ')'){
            String left;
            String right;
            int q = 0;
            int i = 1;
            for (; i < in.length(); i++) {
                if(in.charAt(i) == '(') q++;
                else if(in.charAt(i) == ')') q--;

                if(q == 0) break;
            }
            Node op = new Node(in.charAt(i+1));
            left = in.substring(1, i+1);
            right = in.substring(i+2, in.length()-1);

            op.left = parseTree(left);
            op.right = parseTree(right);

            return op;
        }
        else if(in.length() == 1){
            return new Node(in.charAt(0));
        }
        System.out.println("exception");
        return null;
    }

    private int sol(String s){
        int ans = 0;

        Node root = parseTree(s);
        if(root.val == '0' || root.val=='1') return 0;
        else if (root.val=='X' || root.val=='x') return 1;
        else{
            char whole = calc2(root);
            int t0 = calc(root, 0);
            int t1 = calc(root, 1);
            /*
            if(whole == '0'){
                if(t0 != 0 || t1!=0) System.out.println("error 0");
            }
            else if(whole == '1'){
                if(t0 != 1 || t1!=1) System.out.println("error 1");
            }
            else if(whole == 'x'){
                if(t0 != 0 || t1!=1) System.out.println("error x");
            }
            else  if(whole == 'X'){
                if(t0 != 1 || t1!=0) System.out.println("error X");
            }

            System.out.println(whole + " <= " + s);

             */
            if(whole =='0' || whole=='1') return 0;
            else return 1; //always fix it with operand
        }

    }

    private void run() throws Exception {

        if(false){
            Node t = parseTree("(0^x)");
            char c = calc2(t);
        }
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            String s = sc.nextLine();
            dpc.clear();
            int r = sol(s);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new C().run();
    }

}

class Node{
    public char val;
    public Node left;
    public Node right;
    public Node(char n){
        this.val = n;
    }

    public Node(char n, Node left, Node right){
        this.val = n;
        this.left = left;
        this.right = right;
    }
}

class NodeTarget implements Comparable<NodeTarget>{
    public Node n;
    public char t;

    public NodeTarget(Node nn, char tt){n= nn; t=tt;}

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NodeTarget)) return false;
        NodeTarget o = (NodeTarget) obj;
        return o.n == n && o.t == t;
    }

    @Override
    public int hashCode() {
        return n.hashCode() + (int) t;
    }

    @Override
    public int compareTo(NodeTarget o) {
        return 0;
    }
}