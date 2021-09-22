package Graph.EulerianGraph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by ymlai on 14/4/2017.
 *
 * problem: https://onlinejudge.org/external/102/10203.pdf
 *
 * #UVA #Lv2 #eulerian_graph
 */
public class UVA10203 {
    public static void main (String[] args)throws Exception{
        if(args.length >= 1){
            System.setIn(new FileInputStream(args[0]));
        }

        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        int nc = Integer.parseInt(sc.readLine());
        sc.readLine();
        for(int c=0; c<nc; ++c){
            String line  = sc.readLine();
            String[] token = line.split(" ");
            int x = Integer.parseInt(token[0]);
            int y = Integer.parseInt(token[1]);

            line = sc.readLine();
            double totalDist = 0;
            while(line!= null && line.trim().length() != 0){
                token = line.split(" ");
                int sx = Integer.parseInt(token[0]);
                int sy = Integer.parseInt(token[1]);
                int ex = Integer.parseInt(token[2]);
                int ey = Integer.parseInt(token[3]);
                totalDist += Math.sqrt((ex-sx)*(ex-sx) + (ey-sy)*(ey-sy)) * 2;
                line = sc.readLine();
            }

            double minTime = totalDist / 1000 / 20;
            minTime = minTime * 60;
            long minTimeInt = Math.round(minTime);

            System.out.format("%d:%02d\n",minTimeInt / 60, minTimeInt % 60);
            if(c != nc-1)
                System.out.println();
        }
    }
}
