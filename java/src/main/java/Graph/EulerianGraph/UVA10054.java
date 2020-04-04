package Graph.EulerianGraph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ymlai on 14/4/2017.
 */
public class UVA10054 {
    static List<List<Pair>> AdjList = new ArrayList<>();

    static class Pair{
        public int first;
        public int second;
        public Pair(int a, int b){
            first = a;
            second = b;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        String line = sc.readLine();
        int nc = Integer.parseInt(line);

        for(int i=0; i<51; ++i){
            AdjList.add(new ArrayList<>());
        }

        int[] aList = new int[51];
        int[] bList = new int[51];

        for(int c =0; c<nc; ++c){
            int N =  Integer.parseInt(sc.readLine());
            for(int i=0; i<51; ++i){
                AdjList.get(i).clear();
            }

            for(int i=0; i<N; ++i){
                line = sc.readLine();
                String[] token = line.split(" ");
                int a = Integer.parseInt(token[0]);
                int b = Integer.parseInt(token[1]);
                AdjList.get(a).add(new Pair(b, 1));
                AdjList.get(b).add(new Pair(a, 1));
            }

            boolean missing = false;
            int firstColor = -1;
            for(int i=0; i<51; ++i){
                if(AdjList.get(i).size() %2 != 0) {
                    missing = true;
                    break;
                }
                if(AdjList.get(i).size() > 0)
                    firstColor = i;
            }

            System.out.format("Case #%d\n", c+1);
            if(missing) {
                System.out.println("some beads may be lost");
            }
            else{
                cyc = new LinkedList<Integer>();

                EulerTour(0, firstColor); // cyc contains an Euler tour starting at A

                if(cyc.size() != N){
                    System.out.println("some beads may be lost");
                }
                else{
                    for(int i=0, j=1; i<cyc.size(); ++i, ++j){
                        if(j == cyc.size()) j = 0;
                        int ii = cyc.get(i);
                        int jj = cyc.get(j);
                        System.out.println(ii + " " + jj);
                    }
                }
            }
            System.out.println();
        }
    }

    static List<Integer> cyc; // we need list for fast insertion in the middle
    static void EulerTour(int idx, int u) {
        for (int j = 0; j < (int)AdjList.get(u).size(); j++) {
            Pair v = AdjList.get(u).get(j);
            if (v.second != 0) { // if this edge can still be used/not removed
                v.second = 0; // make the weight of this edge to be 0 (‘removed’)
                for (int k = 0; k < AdjList.get(v.first).size(); k++) {
                    Pair uu = AdjList.get(v.first).get(k);  // remove bi-directional edge once only.
                    if (uu.first == u && uu.second != 0) {
                        uu.second = 0;
                        break;
                    }
                }
                cyc.add(idx, u);
                EulerTour(idx+1, v.first);
            }
        }
    }
}
