package Leetcode.MicrosoftMock;

import java.util.*;

public class OnsiteQ2 {

    public List<Integer> spiralOrder(int[][] matrix) {
        int dir = 0; // 0 right, 1, down, 2 left, 3 up
        int pr = 0, pc = 0;
        int m = matrix.length; int n = matrix[0].length;
        List<Integer> result = new ArrayList<Integer>();

        int minR, maxR, minC, maxC;
        minR = 0; maxR = m-1; minC = 0; maxC = n-1;

        for (int i = 0; i < m*n; i++) {
            //System.out.println("x " + pr + " " + pc);
            result.add(matrix[pr][pc]);
            if(minR == maxR && minC == maxC) break;
            if(dir == 0){
                if(pc+1 > maxC){
                    dir = 1;
                    minR += 1; //end of right, remove the current row
                    ++pr;
                }
                else ++pc;
            }
            else if(dir == 1){
                if(pr+1 > maxR){
                    dir = 2;
                    maxC -= 1; //end of down, go left
                    --pc;
                }
                else ++pr;
            }
            else if(dir == 2){
                if(pc-1 < minC){
                    dir = 3;
                    maxR -= 1; // end of left, go up
                    --pr;
                }
                else --pc;
            }
            else if(dir == 3){
                if(pr-1 < minR){
                    dir = 0;
                    minC += 1; // end of up, go left
                    ++pc;
                }
                else --pr;
            }
        }

        return result;
    }

    public static void main(String[] args){
        OnsiteQ2 s = new OnsiteQ2();
        //s.spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
        s.spiralOrder(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}});

    }
}
