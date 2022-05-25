package ctci.hard;

import java.util.*;

public class CircusTower {
    int[][] people;
    public int largestPossible(int[][] people){
        int n = people.length;
        this.people = people;

        Comparator<int[]> compare = new Comparator<>(){
            public int compare(int[] a, int[] b){
                if(a[0] == b[0])
                    return b[1] - a[1];
                else
                    return b[0] - a[0];
            }
        };

        Arrays.sort(people, compare);
        memo = new int[n][n+1];

        for(int i=0; i<n; ++i){
            Arrays.fill(memo[i], -1);
        }

        return helper(0, -1);
    }

    int[][] memo;

    public int helper(int i, int lastIndex){
        if(i == people.length)
            return 0;

        if(memo[i][lastIndex+1] != -1)
            return memo[i][lastIndex+1];

        memo[i][lastIndex+1] = 0;
        int result = 0;
        if(lastIndex == -1 || people[i][0] < people[lastIndex][0] && people[i][1] < people[lastIndex][1]){
            result = helper(i+1, i)+1;
        }
        memo[i][lastIndex+1] = Math.max(result, helper(i+1, lastIndex));

        return memo[i][lastIndex+1];
    }

    public static void main(String[] args) {
        //(65, 100) (70, 150) (56, 90) (75, 190) (60, 95) (68, 110)
        int[][] data = new int[][]{
                {65, 100}, {70, 150}, {56, 90}, {75, 190}, {60, 95}, {68, 110}
        };
        CircusTower test = new CircusTower();
        System.out.println(test.largestPossible(data));
    }
}
