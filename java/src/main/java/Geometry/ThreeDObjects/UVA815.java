package Geometry.ThreeDObjects;

import java.util.Scanner;

/**
 * Created by ymlai on 24/4/2017.
 *
 * problem: https://onlinejudge.org/external/8/815.pdf
 *
 * #UVA #3d_geometry #Lv3
 */
public class UVA815 {
    static int[][] map = new int[31][31];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int nc = 0;

        while(true){
            int m = sc.nextInt();
            int n=  sc.nextInt();

            if(m == 0 && n == 0)
                break;

            nc++;

            int minL = Integer.MAX_VALUE;
            int maxL = Integer.MIN_VALUE;
            for(int i=0; i<m; ++i)
                for(int j=0; j<n; ++j){
                    map[i][j] = sc.nextInt();
                    if(map[i][j] < minL)
                        minL = map[i][j];
                    if(map[i][j] > maxL)
                        maxL = map[i][j];
                }

            int l = sc.nextInt();

            double low, high, mid;
            low = minL + l*1.0/m/n/100;                //lower bound
            high = maxL + l*1.0/m/n/100;      //upper bound
            while(Math.abs(high-low) > 1e-7){
                mid = (low+high)/2;

                double V = findV(mid, map, m, n);

                if(V < l)
                    low = mid;
                else
                    high= mid;
            }

            int underCnt = 0;
            for(int i=0; i<m; ++i){
                for(int j=0; j<n; ++j){
                    if(map[i][j] < high && !(Math.abs(map[i][j] - high) < 1e-6))
                        underCnt++;
                }
            }

            System.out.format("Region %d\n", nc);
            System.out.format("Water level is %.2f meters.\n", high);
            System.out.format("%.2f percent of the region is under water.\n\n", underCnt*1.0/(m*n)*100);
        }
    }

    private static double findV(double H, int[][] map, int m, int n){
        double r = 0.0;
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                r += Math.max(0, H-map[i][j])*100;
            }
        }
        return r;
    }
}
