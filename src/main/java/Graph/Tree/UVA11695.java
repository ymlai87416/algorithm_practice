package Graph.Tree;

import java.util.*;

/**
 * Created by ymlai on 12/4/2017.
 */
public class UVA11695 {

    static List<List<Integer>> AdjList = new ArrayList<>();
    final static int INF = Integer.MAX_VALUE/2;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int i=0; i<2501; ++i)
            AdjList.add(new ArrayList<>());

        for(int i=0; i<nc; ++i){
            int n = sc.nextInt ();

            for(int j=0; j<n; ++j)
                AdjList.get(j).clear();

            for(int j=0; j<n-1; ++j){
                int a = sc.nextInt();
                int b = sc.nextInt();

                a--;
                b--;

                AdjList.get(a).add(b);
                AdjList.get(b).add(a);
            }

            //first bfs from any node
            int[] d  = new int[n];
            int endpoint1 = findDiameter(d, 0);
            int diameter = d[endpoint1];
            List<Integer> diameterList = findDiameterList(d, endpoint1);
            int endpoint2 = diameterList.get(diameterList.size()-1);

            int shortest = Integer.MAX_VALUE;
            int delA = 0, delB = 0;
            int addA = 0, addB = 0;
            int[] dRight = new int[n];
            int[] dLeft = new int[n];
            for(int ai=0, bi=1; bi<diameterList.size(); ++ai, ++bi){
                int a = diameterList.get(ai);
                int b = diameterList.get(bi);
                AdjList.get(a).remove(new Integer(b));
                AdjList.get(b).remove(new Integer(a));
                int diameterLeft = -1;
                int endPointLeft = 0;
                int diameterRight = -1;
                int endPointRight = 0;

                //Find left tree diameter
                endPointLeft = findDiameter(dLeft, endpoint1);
                diameterLeft = dLeft[endPointLeft];

                //Find right tree diameter
                endPointRight = findDiameter(dRight, endpoint2);
                diameterRight = dRight[endPointRight];

                int resultDiameter = Math.max(diameterLeft-(diameterLeft/2)+diameterRight-(diameterRight/2) + 1,
                        Math.max(diameterLeft, diameterRight));

                /*
                System.out.println("Trying to remove " +  a + " " + b);
                System.out.println("Left tree diameter: " + diameterLeft);
                List<Integer> diameterListLeft = findDiameterList(dLeft, endPointLeft);
                System.out.println("From: " + diameterListLeft.get(0) + " To: " + diameterListLeft.get(diameterListLeft.size()-1));
                System.out.println("Right tree diameter: " + diameterRight);
                List<Integer> diameterListRight = findDiameterList(dRight, endPointRight);
                System.out.println("From: " + diameterListRight.get(0) + " To: " + diameterListRight.get(diameterListRight.size()-1));
                System.out.println("Combined tree diameter: " + resultDiameter);
                System.out.println("Shortest tree diameter found: " + shortest);
                */

                if(resultDiameter < shortest){
                    shortest = resultDiameter;
                    delA = a;
                    delB = b;

                    addA = findMidPoint(dLeft, endPointLeft, diameterLeft);
                    addB = findMidPoint(dRight, endPointRight, diameterRight);
                }

                AdjList.get(a).add(b);
                AdjList.get(b).add(a);
            }

            System.out.println(shortest);
            ++delA; ++delB; ++addA; ++addB;
            System.out.println(Math.min(delA, delB) + " " + Math.max(delA, delB));
            System.out.println(Math.min(addA, addB) + " " + Math.max(addA, addB));
        }
    }

    //return endpoint, diameter can be find in d[returnVal]
    static int findDiameter(int[] d, int startPoint){
        int diameter = 0;
        int endpoint = startPoint;
        Queue<Integer> q = new ArrayDeque<>(); q.offer(startPoint); // start from source
        Arrays.fill(d, INF);
        d[startPoint] = 0;
        while (!q.isEmpty()) {
            int u = q.poll(); // queue: layer by layer!
            for (int j = 0; j < AdjList.get(u).size(); j++) {
                Integer v = AdjList.get(u).get(j); // for each neighbor of u
                if (d[v] == INF) { // if v.first is unvisited + reachable
                    d[v] = d[u] + 1; // make d[v.first] != INF to flag it
                    if(diameter < d[v]){
                        endpoint = v;
                        diameter =d[v];
                    }
                    q.offer(v); // enqueue v.first for the next iteration
                } } }

        Arrays.fill(d, INF);
        d[endpoint] = 0;
        q.offer(endpoint);
        diameter = 0;
        while (!q.isEmpty()) {
            int u = q.poll(); // queue: layer by layer!
            for (int j = 0; j < AdjList.get(u).size(); j++) {
                Integer v = AdjList.get(u).get(j); // for each neighbor of u
                if (d[v] == INF) { // if v.first is unvisited + reachable
                    d[v] = d[u] + 1; // make d[v.first] != INF to flag it
                    if(diameter < d[v]){
                        endpoint = v;
                        diameter =d[v];
                    }
                    q.offer(v); // enqueue v.first for the next iteration
                } } }

        return endpoint;
    }

    static int findMidPoint(int dist[], int last, int D){
        int target = D - (D/2);
        int cur = last;
        while(dist[cur] != target){
            for(int j=0; j<AdjList.get(cur).size(); ++j){
                int p = AdjList.get(cur).get(j);
                if(dist[p] == dist[cur]-1) {
                    cur = p;
                    break;
                }
            }
        }

        return cur;
    }

    static List<Integer> findDiameterList(int[] dist, int last){
        List<Integer> diameterL = new ArrayList<Integer>();
        diameterL.add(last);
        int cur = last;
        while(dist[cur] != 0){
            for(int j=0; j<AdjList.get(cur).size(); ++j){
                int p = AdjList.get(cur).get(j);
                if(dist[p] == dist[cur]-1) {
                    cur = p;
                    diameterL.add(cur);
                    break;
                }
            }
        }
        return diameterL;
    }
}
