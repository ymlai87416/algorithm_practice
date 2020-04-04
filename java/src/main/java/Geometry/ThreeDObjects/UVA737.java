package Geometry.Polygon;

import java.util.Scanner;

/**
 * Created by ymlai on 23/4/2017.
 */
public class UVA737 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int n = sc.nextInt();

            if(n == 0) break;

            int vxmin, vxmax, vymin, vymax, vzmin, vzmax;

            int x, y, z, l;
            x = sc.nextInt();
            y = sc.nextInt();
            z = sc.nextInt();
            l = sc.nextInt();

            vxmin = x; vxmax = x+l;
            vymin = y; vymax = y+l;
            vzmin = z; vzmax = z+l;

            for(int i=1; i<n; ++i){
                x = sc.nextInt();
                y = sc.nextInt();
                z = sc.nextInt();
                l = sc.nextInt();

                vxmin = Math.max(vxmin, x);
                vxmax = Math.min(vxmax, x+l);
                vymin = Math.max(vymin, y);
                vymax = Math.min(vymax, y+l);
                vzmin = Math.max(vzmin, z);
                vzmax = Math.min(vzmax, z+l);
            }

            System.out.println((Math.max(0, vxmax-vxmin))*(Math.max(0, vymax-vymin))*(Math.max(0, vzmax-vzmin)));
        }
    }
}
