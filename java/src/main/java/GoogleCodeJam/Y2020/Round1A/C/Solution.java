package GoogleCodeJam.Y2020.Round1A.C;

import javax.management.relation.RelationNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static BufferedReader reader;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            /*
            FILENAME = "Solution-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
                    */

            boolean useScanner = false;

            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2020\\Round1A\\C\\C-test.in";
            //IN = null;
            if(IN == null) {
                if (useScanner) sc = new Scanner(System.in);
                else reader = new BufferedReader(new InputStreamReader(System.in));
            }
            else {
                if(useScanner) sc = new Scanner(new File(IN));
                else reader = new BufferedReader(new FileReader(IN));
            }
            out = new PrintStream(System.out);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
    private long solve(int[][] m){
        //do nothing
        int R = m.length;
        int C = m[0].length;
        int[][] p = new int[R][C];
        int[][] q = m;
        long interestLevel = 0;

        while(true) {
            int[][] t = p;
            p = q;
            q = t;
            for (int i = 0; i < R; ++i) {
                for (int j = 0; j < C; j++) {
                    interestLevel += p[i][j];
                }
            }

            int elimCount = 0;

            for (int i = 0; i < R; ++i) {
                for (int j = 0; j < C; j++) {
                    //find top
                    //find bottom\
                    if(p[i][j] == 0){
                        q[i][j] = 0;
                        continue;
                    }
                    boolean isElm = false;
                    int sum = 0;
                    int nCnt = 0;
                    for (int k = i-1; k >=0; k--) {
                        if(p[k][j] != 0) {
                            sum += p[k][j];
                            ++nCnt;
                            break;
                        }
                    }
                    if(isElm) break;
                    for (int k = i+1; k < R; k++) {
                        if(p[k][j] != 0) {
                            sum += p[k][j];
                            ++nCnt;
                            break;
                        }
                    }
                    if(isElm) break;
                    //find left
                    for (int k = j-1; k >=0; k--) {
                        if(p[i][k] != 0) {
                            sum += p[i][k];
                            ++nCnt;
                            break;
                        }
                    }

                    //find right
                    for (int k = j+1; k < C; k++) {
                        if(p[i][k] != 0) {
                            sum += p[i][k];
                            ++nCnt;
                            break;
                        }
                    }

                    isElm = (sum > (p[i][j] * nCnt));

                    if(isElm) {
                        q[i][j] = 0;
                        elimCount++;
                    }
                    else
                        q[i][j] = p[i][j];
                }
            }
            //eliminated
            if(elimCount == 0)
                break;
        }

        return interestLevel;
    }



    static
    class Dancer{
        public Dancer(int v, boolean isElim, boolean neighbourChanged){
            value = v; this.isElim = isElim; this.neighbourChanged=neighbourChanged;
        }
        public int value;
        public boolean isElim;
        public boolean neighbourChanged;
        public Dancer top;
        public Dancer bottom;
        public Dancer left;
        public Dancer right;
    }
     */

    //tune for IO
    static class SimpleArray{
        public SimpleArray(int size){
            this.size = size;
            this.data = new int[size];
            this.len=0;
        }
        private int size;
        private int len;
        private int[] data;

        private void add(int val){
            if(len==size) throw new RuntimeException();
            data[len] = val;
            len++;
        }
        private void clear(){this.len = 0;}
        private int length(){return this.len;}
        private int get(int loc){return data[loc];}
    }

    int[] left = new int[100000];
    int[] right = new int[100000];
    int[] top = new int[100000];
    int[] bottom = new int[100000];
    boolean[] neighbourChange = new boolean[100000];

    SimpleArray changeDancer = new SimpleArray(100000);
    SimpleArray noChangeDancer = new SimpleArray(100000);
    SimpleArray changeDancer2 =new SimpleArray(100000);
    SimpleArray noChangeDancer2 = new SimpleArray(100000);

    SimpleArray eliminated = new SimpleArray(100000);
    SimpleArray nextLevel = new SimpleArray(100000);

    private int[] toRC(int R, int C, int idx){
        int[] result = new int[2];
        result[0] = idx / C;
        result[1] = idx % C;

        return result;
    }

    private int toIndex(int R, int C, int r, int c){
        return r*C+c;
    }

    private long solve2(int[][] m){
        //do nothing
        int R = m.length;
        int C = m[0].length;

        changeDancer.clear();
        noChangeDancer.clear();

        long interestLevel = 0;


        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++){
                int cIdx = toIndex(R, C, i, j);

                top[cIdx] = i==0? -1: toIndex(R, C, i-1, j);
                bottom[cIdx] = i==R-1? -1: toIndex(R, C, i+1, j);
                left[cIdx] = j==0? -1: toIndex(R, C, i, j-1);
                right[cIdx] = j==C-1? -1: toIndex(R, C, i, j+1);
                changeDancer.add(cIdx);
            }
        }

        while(true) {

            eliminated.clear();
            nextLevel.clear();

            for (int i=0; i<changeDancer.length(); ++i) {
                int dd = changeDancer.get(i);

                int[] rc = toRC(R, C, dd);
                int curVal = m[rc[0]][rc[1]];
                interestLevel += curVal;
                long sum = 0;
                int cnt = 0;

                if(right[dd] != -1){
                    rc = toRC(R, C, right[dd]);
                    sum += m[rc[0]][rc[1]];
                    cnt++;
                }

                if(left[dd] != -1){
                    rc = toRC(R, C, left[dd]);
                    sum += m[rc[0]][rc[1]];
                    cnt++;
                }

                if(top[dd] != -1){
                    rc = toRC(R, C, top[dd]);
                    sum += m[rc[0]][rc[1]];
                    cnt++;
                }

                if(bottom[dd] != -1){
                    rc = toRC(R, C, bottom[dd]);
                    sum += m[rc[0]][rc[1]];
                    cnt++;
                }

                neighbourChange[dd] = false;

                if(sum > curVal * cnt) {
                    eliminated.add(dd);
                    //rc = toRC(R, C, dd);
                    //System.out.println("shit: " + rc[0] + " " + rc[1]);
                }
                else
                    nextLevel.add(dd);
            }

            for (int i=0; i<noChangeDancer.length(); ++i ) {
                int dd = noChangeDancer.get(i);
                int[] rc = toRC(R, C, dd);
                int curVal = m[rc[0]][rc[1]];
                interestLevel += curVal;
            }

            int elimCnt = eliminated.length();

            changeDancer2.clear();
            noChangeDancer2.clear();

            for (int i=0; i<eliminated.length(); ++i ) {
                int dd = eliminated.get(i);
                if(right[dd] != -1 ){
                    neighbourChange[right[dd]] = true;
                    left[right[dd]] = left[dd] ;
                }

                if(left[dd] != -1){
                    neighbourChange[left[dd]] = true;
                    right[left[dd]] = right[dd];
                }

                if(top[dd] != -1){
                    neighbourChange[top[dd]] = true;
                    bottom[top[dd]] = bottom[dd];
                }

                if(bottom[dd] != -1){
                    neighbourChange[bottom[dd]] = true;
                    top[bottom[dd]] = top[dd];
                }
            }


            for (int i=0; i<nextLevel.length(); ++i) {
                int dd = nextLevel.get(i);
                if(neighbourChange[dd]) changeDancer2.add(dd);
                else noChangeDancer2.add(dd);
            }

            for (int i=0; i<noChangeDancer.length(); ++i ) {
                int dd = noChangeDancer.get(i);
                if(neighbourChange[dd]) changeDancer2.add(dd);
                else noChangeDancer2.add(dd);
            }

            SimpleArray temp = changeDancer;
            changeDancer = changeDancer2;
            changeDancer2 = temp;

            temp = noChangeDancer;
            noChangeDancer = noChangeDancer2;
            noChangeDancer2 = temp;

            if(elimCnt == 0) break;
        }

        return interestLevel;
    }

    private void run() throws Exception {

        int t = Integer.parseInt(reader.readLine());
        String line;
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            line = reader.readLine();
            String[] token = line.split(" ");
            int R = Integer.parseInt(token[0]);
            int C = Integer.parseInt(token[1]);
            int[][] m = new int[R][C];

            for(int p=0; p<R; ++p){
                line = reader.readLine();
                token = line.split(" ");
                for(int q=0; q<C; ++q){
                    m[p][q] = Integer.parseInt(token[q]);
                }
            }

            long result = solve2(m);
            out.format("%d\n", result);
        }
        reader.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}