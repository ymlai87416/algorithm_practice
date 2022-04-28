package Leetcode.Contest277;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 problem: https://leetcode.com/contest/weekly-contest-277/problems/maximum-good-people-based-on-statements/
 level: hard, 15 is brute force, use bit operation.
 solution: final rank 1724 / 18223

 #brute_force #bitmask
 **/



public class D {

    public int maximumGood(int[][] statements) {
        //convert all statement into bit?
        int n  = statements.length;
        int comb = (int)Math.pow(2, n);
        int maxP = 0;

        //we prepare the bitmask for each n person
        int[] statementB = new int[n];
        int[] statementM = new int[n];
        char[] t1 = new char[n];
        char[] t2 = new char[n];

        for(int i=0; i<n; ++i){
            for (int j = 0; j < n; j++) {
                if(statements[i][j] == 2)t2[j] = '1'; else t2[j] = '0';
                if(statements[i][j] == 1)t1[j] = '1';
                else t1[j] = '0';
            }

            statementB[i] = Integer.parseInt(new String(t1), 2);
            statementM[i] = Integer.parseInt(new String(t2), 2);
        }

        for (int i = 0; i<= comb; i++) {
            //now assume all with the bit are good
            boolean isValid = true;
            String r2 = Integer.toBinaryString(i);
            r2 = String.format("%1$" + n + "s", r2).replace(' ', '0');
            int goodP = 0;

            for (int j = 0; j < r2.length(); j++) {
                if(r2.charAt(j)== '1') {
                    goodP += 1;

                    //now check both is good
                    int mask = statementM[j] & i;
                    int smask = statementB[j] | mask;
                    int ok = smask ^ i;

                    if(ok != 0){
                        isValid = false;
                        break;
                    }
                }
            }


            if(isValid){

                maxP = Math.max(maxP, goodP);
            }
        }

        return maxP;
    }

    public static void main(String[] args){
        D s = new D();
        int[][] ss = new int[][]{{2,1,2},{1,2,2},{2,0,2}};
        ss = new int[][]{{2,0},{0,2}};
        System.out.println(s.maximumGood(ss));
    }
/*
    //a person [can be good], if any other he state is good, the other also tell the he is good.
    //but somehow people can exclude each other, but floodfill can find out all the cluster.
    //each person can only belong to 1 group, and this group have a size of good, and a size of bad.
    int n =statements.length;
    List<Set<Integer>> a = new ArrayList<>();

    Stack<Integer> st = new Stack<>();
    int[] group = new int[n];
    int gIdx = 0;
        for (int i = 0; i < n; i++) {
        if(group[i] != -1) continue;
        //assume i is a good person
        st.add(i);

        boolean contradict = false;
        while(!st.isEmpty()) {
            group[i] = 99;
            for (int j = 0; j < n; j++) {
                if (statements[i][j] == 1 && (statements[j][i] == 2 || statements[j][i] == 1))
                    st.add(j);
                else{
                    //contradiction
                    contradict = true;
                    break;
                }
            }
        }

        if(!contradict)
            group[i] = -1;
    }

        for (int i = 0; i < n; i++) {
        //assume i is good person
        for (int j = 0; j < n; j++) {
            if(statements[i][j] == 1 && (statements[j][i] == 2 || statements[j][i] == 1))

        }
        //if no other conflict with him, then add all his friend to this group
    }
    */
}
