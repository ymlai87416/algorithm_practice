package Leetcode.Biweek70;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 problem: https://leetcode.com/problems/number-of-ways-to-divide-a-long-corridor/
 level: hard
 solution: DP is TLE only way is to use math

 #math
 **/

public class NumberOfWaysToDivideALongCorridor {

    Map<String, Integer> result = new HashMap<>();

    public int numberOfWays(String corridor) {
        /*
        result.clear();

        int ts = 0;
        for (int i = 0; i < corridor.length(); i++) {
            if(corridor.charAt(i) == 'S') ts+=1;
        }

        if(ts%2 == 1) return 0;
        return dpHelper(corridor);
         */

        return combination(corridor);
    }

    public int combination(String corridor){

        int seat = 0;
        int interPlant = 0;
        int startS=-1, endS=-1;

        int ts = 0;
        for (int i = 0; i <corridor.length(); i++) {
            char ci = corridor.charAt(i);
            if(ci == 'S'){
                ts +=1;
                if(startS == -1) startS = i;
                if(endS < i) endS = i;
            }
        }

        if(ts%2 ==1) return 0;
        if(ts == 0)return 0;

        List<Integer> interPlantList = new ArrayList<>();
        for (int i = startS; i < endS+1; i++) {
            char ci = corridor.charAt(i);
            if(ci == 'S') seat += 1;
            if(seat==3){
                seat=1;

                interPlantList.add(interPlant);
                interPlant = 0;
            }
            else if(seat==2 && ci == 'P'){
                interPlant +=1;
            }
        }

        long t = 1;
        for (Integer i: interPlantList) {
            t = t * (i+1) % 1000000007;
        }

        return (int)t;
    }

    //even dp get time limited exceeded.
    public int dpHelper(String corridor){

        //we cannot count the way one by one
        //we can continue to count if there is a plant, dp
        if(corridor.length() == 0)
            return 1;
        //System.out.println("D2 " + corridor);

        if(result.containsKey(corridor)) return result.get(corridor);

        int seat = 0;
        long way = 0;
        for(int i=0; i<corridor.length(); ++i){
            if(corridor.charAt(i) == 'S') {
                seat += 1;
                if (seat == 2){
                    way = (way + dpHelper(corridor.substring(i+1)) ) % 1000000007;
                }
                else if(seat == 3)
                    break;
            }
            else if(corridor.charAt(i) == 'P'){
                if (seat==2){
                    way = (way + dpHelper(corridor.substring(i+1)) ) % 1000000007;
                }
            }
        }

        //System.out.println("D " + corridor + " " + way);
        result.put(corridor, (int)way);
        return (int)way;
    }


    public static void main(String[] args){
        String s1 = "SSPPSPS";
        String s2 = "PPSPSP";
        String s3 = "S";

        NumberOfWaysToDivideALongCorridor s  = new NumberOfWaysToDivideALongCorridor();


        System.out.println(s.numberOfWays(s1));
        System.out.println(s.numberOfWays(s2));
        System.out.println(s.numberOfWays(s3));
    }
}
