package Leetcode;

import java.util.ArrayList;
import java.util.List;

/*
number: 51
url: https://leetcode.com/problems/n-queens/
level: hard
solution: just recursion, mark row, col, diag1, diag2
 */

public class NQueens {
    public static void main(String[] args) {
        int n = 4;
        Solution sol = new Solution();
        List<List<String>> r = sol.solveNQueens(n);
        System.out.println(r);
    }

    static
    class Solution {
        //queen attack each other when they are same col, abs(col-row) same, abs(col+row) same
        int[] c;
        boolean[] c_s_w;
        boolean[] c_a_w;
        public List<List<String>> solveNQueens(int n) {
            c= new int[n];
            for(int i=0; i<n; ++i) c[i] = -1;
            c_s_w = new boolean[2*n-1];
            c_a_w = new boolean[2*n-1];
            List<List<String>> r = new ArrayList<>();

            backTrack(0, n, r);

            return r;
        }

        private void backTrack(int row, int n, List<List<String>> r){
            /*
            System.out.print(row + ":");
            for(int i=0; i<n; ++i) System.out.print(" " + c[i]);
            System.out.println();
            */

            for(int i=0; i<n; ++i){
                if(c[i]==-1 && !c_s_w[i-row+n-1] && !c_a_w[i+row]) {
                    if(row == n-1) {
                        c[i] = row;
                        ArrayList<String> tr = new ArrayList<String>();
                        StringBuilder template = new StringBuilder();
                        for(int p=0; p<n; ++p)
                            template.append('.');
                        for(int p=0; p<n; ++p) tr.add(null);
                        for(int p=0; p<n; ++p){
                            template.setCharAt(p, 'Q');
                            tr.set(c[p], template.toString());
                            template.setCharAt(p, '.');
                        }
                        r.add(tr);
                        c[i] = -1;
                    }
                    else {
                        c[i] = row;
                        c_s_w[i - row + n - 1] = true;
                        c_a_w[i + row] = true;
                        backTrack(row + 1, n, r);
                        c[i] = -1;
                        c_s_w[i - row + n - 1] = false;
                        c_a_w[i + row] = false;
                    }
                }
            }
        }
    }
}
