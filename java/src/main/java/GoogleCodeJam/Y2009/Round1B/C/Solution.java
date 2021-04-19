package GoogleCodeJam.Y2009.Round1B.C;


/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2009\\Round1B\\C\\C-test.in";
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

    static class State implements Comparable<State>{
        public String expr;
        public int val;
        public int x;
        public int y;

        public State(String s, int x, int y, int val){
            this.expr = s;
            this.x = x;
            this.y = y;
            this.val = val;
            /*
            this.val = s.charAt(0)-'0';
            for(int i=1; i<s.length(); i+=2){
                if(s.charAt(i) == '+'){
                    this.val = this.val + (s.charAt(i+1)-'0');
                }
                else{
                    this.val = this.val - (s.charAt(i+1)-'0');
                }
            }*/
        }

        @Override
        public int compareTo(State o) {
            if(expr.length() < o.expr.length()) return -1;
            else if(expr.length() > o.expr.length()) return 1;
            else{
                if(this.expr.compareTo(o.expr) == 0){
                    if(this.x == o.x){
                        return this.y - o.y;
                    }
                    else return this.x - o.x;
                }
                else return this.expr.compareTo(o.expr);
            }
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof State){
                State s = (State) obj;
                return this.expr.compareTo(s.expr) == 0 && this.x == s.x && this.y == s.y;
            }
            else return false;
        }
    }

    int[][] moves2 = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    //MLE
    private String[] solveSmall(char[][] matrix, int[] queries) {
        String[] ans = new String[queries.length];

        PriorityQueue<State> q = new PriorityQueue<>();


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] != '+' && matrix[i][j] != '-')
                    q.add(new State("" + matrix[i][j], i, j, matrix[i][j]-'0'));
            }
        }

        while(!q.isEmpty()){
            State s = q.poll();

            State nextS = q.peek();
            while(nextS!= null && s.equals(nextS)){
                q.poll();
                nextS = q.peek();
            }

            //System.out.println("D " + s.expr + " V: " + s.val + " x: " + s.x + " y: " + s.y);
            boolean allFill = true;
            for (int i = 0; i < queries.length; i++) {
                if(s.val == queries[i] && ans[i] == null)
                    ans[i] = s.expr;

                if(ans[i] == null) allFill= false;
            }
            if (allFill) break;
            //consider all 16 cases
            for (int i = 0; i < 16; i++) {
                int move1st = i/4;
                int move2nd = i%4;

                int dx =moves2[move1st][0]; int dy=  moves2[move1st][1];
                int d2x = moves2[move1st][0]+ moves2[move2nd][0];
                int d2y = moves2[move1st][1]+ moves2[move2nd][1];

                Character c1 = safeGet(matrix, s.x +dx, s.y+dy);
                Character c2 = safeGet(matrix, s.x + d2x, s.y + d2y);
                if(c1 == null || c2 == null)
                    continue;

                int newVal = s.val;
                if(c1 == '+')
                    newVal += c2-'0';
                else
                    newVal -= c2-'0';

                State newS = new State(s.expr+c1+c2, s.x+ d2x, s.y+ d2y, newVal);
                q.offer(newS);
            }
        }

        return ans;
    }

    private Character safeGet(char[][] matrix, int x, int y){
        if(x < 0 || x >=matrix.length) return null;
        if(y < 0 || y >=matrix[0].length) return null;
        return matrix[x][y];
    }

    int dp[][][][] = new int[20][20][251][3];
    int[] checkAns = new int[251];
    char[][] m;

    int offset = 0;

    private String[] solveDp(char[][] matrix, int[] queries) {
        this.m = matrix;

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                for (int k = 0; k < 251; k++) {
                    dp[i][j][k][0] = -1;
                }
            }
        }

        for (int i = 0; i < 251; i++) {
            checkAns[i] = 0;
        }
        
        for(int i=0; i<matrix.length; ++i){
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] != '+' && matrix[i][j] != '-') {
                    int v = matrix[i][j] - '0';
                    checkAns[v] = 1;
                    dp[i][j][v][0] = 0;
                    dp[i][j][v][1] = i;
                    dp[i][j][v][2] = j;
                }
            }
        }

        int currRound = 0;

        while(true){
            System.out.println("D: " + currRound);

            //check condition
            boolean allGood = true;
            for (int i = 0; i < queries.length; i++) {
                if(checkAns[queries[i]] == 0) {
                    System.out.println("no value: " + queries[i]);
                    allGood = false;
                    break;
                }
            }

            if(allGood) break;

            for(int i=0; i<matrix.length; ++i){
                for (int j = 0; j < matrix[i].length; j++) {
                    if(matrix[i][j] != '+' && matrix[i][j] != '-') {
                        for (int l = 0; l < 251; l++) {
                            if (dp[i][j][l][0] == currRound) {

                                for (int k = 0; k < 16; k++) {
                                    int move1st = k / 4;
                                    int move2nd = k % 4;

                                    int cx = dp[i][j][l][0];
                                    int cy = dp[i][j][l][1];
                                    int dx = moves2[move1st][0];
                                    int dy = moves2[move1st][1];
                                    int d2x = moves2[move1st][0] + moves2[move2nd][0];
                                    int d2y = moves2[move1st][1] + moves2[move2nd][1];

                                    Character c1 = safeGet(matrix, cx + dx, cy + dy);
                                    Character c2 = safeGet(matrix, cx + d2x, cy + d2y);
                                    if (c1 == null || c2 == null)
                                        continue;

                                    int delta = 0;
                                    if (c1 == '+') delta += c2 - '0';
                                    else delta -= c2 - '0';

                                    if (l + delta >= offset && dp[i][j][l + delta][0] == -1) {
                                        //current round
                                        dp[i][j][l + delta][0] = currRound + 1;
                                        dp[i][j][l + delta][1] = cx + d2x;
                                        dp[i][j][l + delta][2] = cy + d2y;
                                        checkAns[l + delta] = 1;
                                        System.out.println("put value: " + (l + delta));
                                    }
                                }
                            }
                        }
                    }
                }
            }

            currRound+=1;
        }

        //for each query, backtrack
        String[] ans = new String[queries.length];
        for(int i=0; i<queries.length; ++i){
            //find the min shit
            int qv = queries[i];
            int m = Integer.MAX_VALUE;
            int mx = -1;
            int my = -1;

            for (int j = 0; j < matrix.length; j++) {
                for (int k = 0; k < matrix[j].length; k++) {
                    if(dp[j][k][qv][0] != -1){
                        if(dp[j][k][qv][0] <m) {
                            m = dp[j][k][qv][0];
                            mx = j;
                            my = k;
                        }
                        else if(dp[j][k][qv][0] == m){
                            if(mx != -1 && matrix[j][k] < matrix[mx][my]){
                                mx = j;
                                my = k;
                            }
                            else{
                                String a = backTrack(qv, j, k);
                                String b = backTrack(qv, mx, my);
                                if(a.compareTo(b) < 0){
                                    mx = j;
                                    my = k;
                                }
                            }
                        }
                    }
                }
            }

            ans[i] = backTrack(qv, mx, my);
        }
        return ans;
    }

    private String backTrack(int target, int x, int y){
        String ans = null;
        int curVal = m[x][y] -'0';
        int step = dp[x][y][target][0];
        for (int k = 0; k < 16; k++) {
            int move1st = k / 4;
            int move2nd = k % 4;

            int dx = moves2[move1st][0];
            int dy = moves2[move1st][1];
            int d2x = moves2[move1st][0] + moves2[move2nd][0];
            int d2y = moves2[move1st][1] + moves2[move2nd][1];

            char op = m[x+dx][y+dy];
            String temp = null;
            if(op == '+') {
                int nextT = target - curVal;
                if(dp[x+d2x][y+d2y][nextT][0] == step-1)
                    temp = backTrack(nextT, x + d2x, y + d2y);
            }
            else {
                int nextT = curVal - target;
                if(dp[x+d2x][y+d2y][nextT][0] == step-1)
                    temp = backTrack(nextT, x + d2x, y + d2y);
            }

            if(temp != null){
                if(ans == null)
                    ans = temp;
                else{
                    if(temp.compareTo(ans) < 0)
                        ans = temp;
                }
            }
        }

        return ans;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int W = sc.nextInt();
            int Q = sc.nextInt();
            
            char[][] matrix = new char[W][W];
            sc.nextLine();
            for (int j = 0; j < W; j++) {
                String s = sc.nextLine();
                for (int k = 0; k <W; k++) {
                    matrix[j][k] = s.charAt(k);
                }
            }

            int[] queries = new int[Q];
            for (int j = 0; j < Q; j++) {
                queries[j] = sc.nextInt();
            }
            
            out.print("Case #" + i + ":\n");

            //String[] ans = solveSmall(matrix, queries);
            String[] ans = solveDp(matrix, queries);

            for (int j = 0; j < ans.length; j++) {
                System.out.println(ans[j]);
            }
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}