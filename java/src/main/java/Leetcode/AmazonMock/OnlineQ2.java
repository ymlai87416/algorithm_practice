package Leetcode.AmazonMock;

import java.util.HashMap;

public class OnlineQ2 {

    public int numEquivDominoPairs(int[][] dominoes) {
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < dominoes.length; i++) {
            int domN;
            if(dominoes[i][0] > dominoes[i][1])
                domN = dominoes[i][1]*10 + dominoes[i][0];
            else
                domN = dominoes[i][0]*10 + dominoes[i][1];

            if(!cnt.containsKey(domN))
                cnt.put(domN, 0);

            cnt.put(domN, cnt.get(domN)+1);
        }

        int sum = 0;
        for (Integer k: cnt.keySet()
             ) {
            int cc = cnt.get(k);
            sum += (cc * (cc-1))/2;
        }

        return sum;
    }

    public static void main(String[] args){
        int[] dominoes = new int[]{};

    }

}
