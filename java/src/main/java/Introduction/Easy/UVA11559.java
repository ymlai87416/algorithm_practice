package Introduction.Easy;

import java.util.Scanner;

/**
 * Created by Tom on 13/4/2016.
 *
 */
public class UVA11559 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            if(!sc.hasNext()) break;

            int p = sc.nextInt();
            int b = sc.nextInt();
            int h = sc.nextInt();
            int w = sc.nextInt();

            int mincost = b+1;
            for(int i=0; i<h; ++i){
                boolean found=false;
                int hmoney = sc.nextInt();

                for(int j=0; j<w; ++j){
                    int nbed = sc.nextInt();
                    if(found) continue;
                    if(nbed >= p){
                        int cost = p * hmoney;
                        if(cost < mincost) mincost = cost;
                        found=true;
                    }
                }
            }
            sc.nextLine();

            if(mincost == b+1)
                System.out.println("stay home");
            else
                System.out.println(mincost);

        }
    }
}
