package Leetcode.Contest276;

/**
 problem:
 level: medium
 solution: always * 2 at the end. use greedy

 643 -> 655 = 12 mins
 #math
 **/

public class MinimumMovesToReachTargetScore {

    public int minMoves(int target, int maxDoubles) {
        int r=0;
        while(maxDoubles > 0 && target!= 1){
            maxDoubles -= 1;

            if(target %2 == 0) r+=1;
            else r+=2;
            target /= 2;
        }

        //now we left are add
        r += target - 1;
        return r;
    }


    public static void main(String[] args){
        MinimumMovesToReachTargetScore s = new MinimumMovesToReachTargetScore();
        s.minMoves(5, 0);
        s.minMoves(19, 2);
        s.minMoves(10, 4);
    }
}
