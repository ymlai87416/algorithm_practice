package Geometry.Quadrilaterals;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Created by ymlai on 23/4/2017.
 */
public class UVA11207 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int n = sc.nextInt();

            if(n == 0) break;

            BigDecimal maxL=BigDecimal.valueOf(-1);
            int maxC=0;

            for(int i=0; i<n; ++i){
                int a = sc.nextInt();
                int b = sc.nextInt();

                int maxab = Math.max(a, b);
                int minab = Math.min(a, b);

                BigDecimal fL = BigDecimal.ZERO;
                fL = BigDecimal.valueOf(maxab).divide(BigDecimal.valueOf(4)).min(BigDecimal.valueOf(minab)); //cut it horizontal 4 piece.
                fL = BigDecimal.valueOf(minab).divide(BigDecimal.valueOf(2)).max(fL); //cut it a square and then cut 4 square.

                if(maxL.compareTo(fL) < 0){
                    maxL = fL;
                    maxC = i+1;
                }
            }

            System.out.println(maxC);
        }
    }
}
