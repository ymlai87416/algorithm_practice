package ProblemSolving.IterativeThreeLoopHarder;

import java.util.Scanner;

/**
 * Created by Tom on 20/4/2016.
 */
public class UVA11565 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int t  = sc.nextInt();
        for(int i=0; i<t; ++i){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            boolean solutionf = false;
            for(int x=-100; x<=100 && !solutionf; ++x){
                for(int y=-100; y<=a-x && y<=100 && !solutionf; ++y){
                    if(y==x) continue;
                    for(int z=-100; z<=a-x-y && z<=100 && !solutionf; ++z){
                        if(z==x || z==y) continue;
                        int tempa = x+y+z; if(tempa != a) continue;
                        long tempb = x*y*z; if(tempb != b) continue;
                        int tempc = x*x + y*y+z*z; if(tempc !=c) continue;
                        System.out.format("%d %d %d\n", x, y, z);
                        solutionf =true;
                        break;
                    }
                }
            }
            if(!solutionf) System.out.println("No solution.");
        }
    }
}
