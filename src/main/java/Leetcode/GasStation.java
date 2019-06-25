package Leetcode;

public class GasStation {
    public static void main(String[] args){
        int[] gas= new int[]{2,3,4};
        int[] cost = new int[]{3,4,3};

        Solution s = new Solution();
        System.out.println(s.canCompleteCircuit(gas, cost));
    }


    static
    class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int[] diff = new int[gas.length];
            int netGas = 0;
            for(int i=0; i<gas.length; ++i){
                diff[i] = gas[i] - cost[i];
                netGas += diff[i];
            }

            if(netGas >= 0) {
                boolean canCycle = false;
                int start = 0;
                while(!canCycle){
                    int run = 0;
                    canCycle= true;

                    for (int i = 0; i < diff.length; ++i) {
                        int ii = (i + start) % diff.length;
                        run += diff[ii];
                        if (run < 0) {
                            canCycle = false;
                            start = ii+1;  //if car failed here, no way diff[ii] is positive and hence start at next point.
                            break;
                        }
                    }
                }

                return start;
            }
            else
                return -1;
        }
    }

}
