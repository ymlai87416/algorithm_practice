package Introduction.RealLifeProblemEasier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by Tom on 15/4/2016.
 *
 * Last time Wrong answer.
 */
public class UVA161 {

    static class Light{
        int len;
        int cur;
        int color;
        public Light(int len) { this.len = len; cur=0; color=0;}
        public void incr() {
            cur++;
            if(cur == len) {
                cur -= len;
                if(color == 1) color=2;
                else if(color==2) color=0;
            }
            if(color==0 && cur==len-5) {color=1; hadOrange=true;}
        }

        public boolean isG(){
            return color==0;
        }
        boolean hadOrange = false;
    }
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);

        while(true){
            ArrayList<Integer> ls = new ArrayList<Integer>();
            while(true){
                try {
                    String bs = sc.next();
                    int b = Integer.parseInt(bs);
                    if(b == 0) break;
                    ls.add(b);
                } catch(Exception ex) {}
            }

            if(ls.size()==0) {
                sc.nextInt(); sc.nextInt();
                break;
            }

            Light[] g = new Light[ls.size()];
            for(int i=0; i<g.length; ++i)
                g[i] = new Light(ls.get(i));

            int xxi=0;
            boolean result =false;
            for(xxi=0; xxi< 5*60*60-1; ++xxi){

                for(int xi=0; xi<g.length; ++xi)
                    g[xi].incr();

                boolean allG = true;
                boolean allO = false;
                for(int xi=0; xi<g.length; ++xi){
                    allG = allG &&  g[xi].isG();
                    allO = allO ||  g[xi].hadOrange;
                    if(!allG) break;
                }
                result= allG && allO;
                if(result) break;
            }

            Calendar c = Calendar.getInstance();
            c.set(1900, 1, 1, 0, 0, 0);
            c.add(Calendar.SECOND, xxi+1);

            if(result)
                System.out.format("%02d:%02d:%02d\n", c.get(Calendar.HOUR), c.get(Calendar.MINUTE), c.get(Calendar.SECOND));
            else
                System.out.println("Signals fail to synchronise in 5 hours");
        }
    }
}
