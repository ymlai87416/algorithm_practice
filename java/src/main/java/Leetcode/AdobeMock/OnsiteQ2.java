package Leetcode.AdobeMock;

import java.lang.reflect.Array;
import java.util.*;

public class OnsiteQ2 {

    public void rotate(int[][] matrix) {
        //we do it ring by ring, should be ok


        int n = matrix.length;
        int minR=0, maxR=n, minC=0, maxC=n;

        //no need to do 1
        //we walk left, down, right, up and left again, until we have filled all the 4+4n-8= 4n-4;

        for(int i=n; i>1; i-=2){
            Queue<Integer> st=  new ArrayDeque<Integer>();
            int pr = minR; int pc= minC;
            int toBeFilled = 4*i-4;
            int dir = 0;

            while(toBeFilled > 0){

                System.out.println("D" + pr + " " + pc);

                st.offer(matrix[pr][pc]);
                if(st.size() == i){
                    //pop the front,
                    matrix[pr][pc] = st.poll();
                    toBeFilled--;
                }

                //left, pc meet maxC
                if(dir == 0){
                    if(pc+1 >= maxC){
                        dir = 1;
                        //turn down
                        pr++;
                    }
                    else pc++;
                }
                //down, pr meet maxR
                else if(dir == 1){
                    if(pr+1 >= maxR){
                        dir = 2;
                        //turn down
                        pc--;
                    }
                    else pr++;
                }
                //left, pc mean minC
                else if(dir == 2){
                    if(pc-1 < minC){
                        dir = 3;
                        //turn down
                        pr--;
                    }
                    else pc--;
                }
                //up, pr meat minR;
                else if(dir == 3){
                    if(pr-1 < minR){
                        dir = 0;
                        //turn down
                        pc++;
                    }
                    else pr--;
                }
            }

            minR++; maxR--; minC++; maxC--;
        }

    }

    public static void main(String[] args){
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        matrix = new int[][] {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        OnsiteQ2 s = new OnsiteQ2();
        s.rotate(matrix);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
