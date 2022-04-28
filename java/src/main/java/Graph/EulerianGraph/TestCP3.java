package Graph.EulerianGraph;

import java.util.*;

public class TestCP3 {
    static class Pair{
        int first;
        int second;

        public Pair(int f, int s){
            this.first = f;
            this.second = s;
        }
    }


    static List<Integer> cyc; // we need list for fast insertion in the middle
    static void EulerTour(int idx, int u) {
        for (int j = 0; j < (int)AdjList.get(u).size(); j++) {
            Pair v = AdjList.get(u).get(j);
            if (v.second != 0) { // if this edge can still be used/not removed
                v.second = 0; // make the weight of this edge to be 0 (‘removed’)
                /*
                for (int k = 0; k < AdjList.get(v.first).size(); k++) {
                    Pair uu = AdjList.get(v.first).get(k);  // remove bi-directional edge once only.
                    if (uu.first == u && uu.second != 0) {
                        uu.second = 0;
                        break;
                    }
                }*/
                System.out.println("D" + idx + " " + (u+1));
                cyc.add(idx, u);
                EulerTour(idx+1, v.first);
            }
        }
    }
    static ArrayDeque<Integer> cyc2;
    static void dfs(int u) {
        for (int j = 0; j < (int)AdjList.get(u).size(); j++) {
            Pair v = AdjList.get(u).get(j);
            if (v.second != 0) { // if this edge can still be used/not removed
                v.second = 0; // make the weight of this edge to be 0 (‘removed’)

                dfs(v.first);
            }
        }
        cyc2.addFirst(u);
    }


    static List<List<Pair>> AdjList;
    public static void main(String[] args){
        //now we create a 6 node graph
        AdjList = new ArrayList<>();
        for(int i=0; i<6; ++i){
            AdjList.add(new ArrayList<>());
        }


        AdjList.get(0).add(new Pair(2, 1));
        AdjList.get(0).add(new Pair(1, 1));

        AdjList.get(1).add(new Pair(3, 1));
        AdjList.get(1).add(new Pair(3, 1));
        AdjList.get(1).add(new Pair(1, 1));

        AdjList.get(2).add(new Pair(4, 1));
        AdjList.get(2).add(new Pair(1, 1));
        AdjList.get(2).add(new Pair(0, 1));

        AdjList.get(3).add(new Pair(5, 1));
        AdjList.get(3).add(new Pair(2, 1));

        AdjList.get(4).add(new Pair(5, 1));

        AdjList.get(5).add(new Pair(2, 1));

        cyc = new ArrayList<>();

        System.out.println("Running CP3");
        EulerTour(0, 0);
        for(int i=0; i<cyc.size(); ++i)
            System.out.print((cyc.get(i)+1) + " ");
        System.out.println();

        System.out.println("Running Hierholzer's");
        for(int i=0; i<AdjList.size(); ++i)
            for(int j=0; j<AdjList.get(i).size(); ++j)
                AdjList.get(i).get(j).second = 1;

        cyc2 = new ArrayDeque<>();
        dfs( 0);
        while(!cyc2.isEmpty()) {
            System.out.print((cyc2.pollFirst()+1) + " ");
        }
        System.out.println();
    }
}
