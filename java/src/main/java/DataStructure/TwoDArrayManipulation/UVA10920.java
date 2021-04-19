package DataStructure.TwoDArrayManipulation;

import java.util.Scanner;

/**
 * Created by Tom on 15/5/2016.
 *
 * problem: https://onlinejudge.org/external/109/10920.pdf
 *
 * #2DArray
 */
public class UVA10920 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            long nsize = sc.nextLong();
            long index = sc.nextLong();

            if(nsize == 0 && index == 0) break;

            long pi=1;
            for(pi=1; ; pi+=2){
                if(pi*pi> index) break;
            }

            pi -= 2;

            long cnt = pi*pi;
            long x = nsize/2+1 + pi/2;
            long y = x;

            if(cnt != index){
                x++;
                pi+=2;
                cnt++;
                for(int i =0;i<4; ++i) {
                    for (int j = 1; j < (i==0?pi-1:pi); ++j) {
                        //System.out.format("%d Line = %d, column = %d.\n", cnt, x, y);
                        if (index == cnt) break;
                        if (i == 0) y--;
                        else if (i == 1) x--;
                        else if (i == 2) y++;
                        else if (i == 3) x++;
                        cnt++;
                    }
                    if(index == cnt) break;
                }
            }


            System.out.format("Line = %d, column = %d.\n", x, y);
        }
    }
}
