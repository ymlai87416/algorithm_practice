package Leetcode;

import java.util.HashMap;

public class FourSumII {
    public static void main(String[] args) {
        int[] A = new int[]{1, 2};
        int[] B = new int[]{-2, -1};
        int[] C = new int[]{-1, 2};
        int[] D = new int[]{0, 2};
        Solution sol = new Solution();
        int r = sol.fourSumCount(A, B, C, D);
        System.out.println(r);
    }

    static
    class Solution {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            //ab and cd
            HashMap<Integer, Integer> ab = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> cd = new HashMap<Integer, Integer>();

            int n = A.length;

            for(int i=0; i<n; ++i){
                for(int j=0; j<n; ++j){
                    int tab = A[i] + B[j];
                    int tcd = C[i] + D[j];

                    if(ab.containsKey(tab))
                        ab.put(tab, ab.get(tab)+1);
                    else
                        ab.put(tab, 1);

                    if(cd.containsKey(tcd))
                        cd.put(tcd, cd.get(tcd)+1);
                    else
                        cd.put(tcd, 1);
                }
            }

            int r=0;

            int p, q, t;
            for(int i : ab.keySet()){
                t = 0-i;
                p = ab.get(i);
                if(cd.containsKey(t)){
                    q = cd.get(t);
                    r += p * q;
                }
            }

            return r;
        }
    }
}
