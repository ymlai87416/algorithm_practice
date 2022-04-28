package Leetcode;


import java.util.*;

public class MinimumNumberOfRefuelingStop {

    int target;
    int[][] stations;
    int startFuel;
    int n;
    int MAX_VALUE = 99999999;

    public static void main(String[] args){
        MinimumNumberOfRefuelingStop c  = new MinimumNumberOfRefuelingStop();
        int target = 100;
        int startFuel = 10;
        int[][] stations = {{10,60},{20,30},{30,30},{60,40}};
        System.out.println(c.minRefuelStops(target, startFuel, stations));

        /*
        100
        1
        [[10,100]]
        100
        10
        [[10,60],[20,30],[30,30],[60,40]]
         */
    }


    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        this.stations = stations;
        this.n = stations.length;
        this.target = target;
        this.startFuel = startFuel;

        return treeSetHelper();

        /*
        this.dp = new HashMap<>();
        int rr = dpHelper(-1, startFuel);
        return rr == MAX_VALUE ? -1 : rr;
        */
    }

    private int[] gasStations(int x){
        if(x == -1) return new int[]{0, startFuel};
        else if(x == n) return new int[]{target, 0};
        else return stations[x];
    }

    private int treeSetHelper(){
        TreeSet<Integer> a = new TreeSet<>(Collections.reverseOrder());

        int curPos = 0;
        int fuel = startFuel;
        int rCnt = 0;
        for(int i=0; i<=n; ++i){
            int[] cur = gasStations(i);
            System.out.println("D" + fuel);
            while(cur[0] - curPos > fuel){
                if(a.size() > 0){
                    fuel += a.pollFirst();
                    rCnt++;
                }
                else
                    return -1;
            }
            fuel -= (cur[0] - curPos);
            curPos = cur[0];

            a.add(cur[1]);
        }

        return rCnt;
    }
}
