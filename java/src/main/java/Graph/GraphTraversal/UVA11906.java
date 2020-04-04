package Graph.GraphTraversal;

import java.util.*;

/**
 * Created by Tom on 25/4/2016.
 * use up 3 mins to read, next time do it. start at 3: 10 and end at 4:02, total time spend=55 minutes.
 * Should reuse the code structure instead of v1, v2, v3...
 * Miss the test case when M/N==0.
 */
public class UVA11906 {

    static int R, C, M, N;
    static boolean visited[][];
    static int reachNo[][];
    static boolean water[][];

    static class Pair implements Comparable<Pair>{
        int first, second;
        public Pair(int a, int b){
            this.first = a;
            this.second = b;
        }

        @Override
        public int compareTo(Pair o) {
            if(first == o.first) return second - o.second;
            else return first - o.first;
        }
    }

    public static void bfs(Pair s){
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(s);
        visited = new boolean[R][C];
        reachNo = new int[R][C];
        for(int i=0; i<R; ++i) {
            Arrays.fill(visited[i], false);
            Arrays.fill(reachNo[i], -1);
        }
        visited[s.first][s.second] = true;

        while(!queue.isEmpty()){
            Pair u = queue.poll();

            TreeSet<Pair> all= new TreeSet<Pair>();

            int cnt =0;
            Pair v1 = new Pair(u.first+M, u.second+N);
            if(!(v1.first < 0 || v1.first >= R || v1.second < 0 || v1.second >= C)){
                if(!water[v1.first][v1.second]) {
                    all.add(v1);
                    if (!visited[v1.first][v1.second]) {
                        visited[v1.first][v1.second] = true;
                        queue.add(v1);
                    }
                }
            }

            Pair v2 = new Pair(u.first-M, u.second+N);
            if(!(v2.first < 0 || v2.first >= R || v2.second < 0 || v2.second >= C) ){
                if(!water[v2.first][v2.second]){
                    all.add(v2);
                    if(  !visited[v2.first][v2.second] ) {
                        visited[v2.first][v2.second] = true;
                        queue.add(v2);
                    }
                }

            }

            Pair v3 = new Pair(u.first+M, u.second-N);
            if(!(v3.first < 0 || v3.first >= R || v3.second < 0 || v3.second >= C)){
                if(!water[v3.first][v3.second]){
                    all.add(v3);
                    if(!visited[v3.first][v3.second] ) {
                        visited[v3.first][v3.second] = true;
                        queue.add(v3);
                    }
                }

            }

            Pair v4 = new Pair(u.first-M, u.second-N);
            if(!(v4.first < 0 || v4.first >= R || v4.second < 0 || v4.second >= C)){
                if(!water[v4.first][v4.second]){
                    all.add(v4);
                    if(!visited[v4.first][v4.second] ) {
                        visited[v4.first][v4.second] = true;
                        queue.add(v4);
                    }
                }
            }

            Pair v5 = new Pair(u.first+N, u.second+M);
            if(!(v5.first < 0 || v5.first >= R || v5.second < 0 || v5.second >= C)) {
                if(!water[v5.first][v5.second]){
                    all.add(v5);
                    if(!visited[v5.first][v5.second] ) {
                        visited[v5.first][v5.second] = true;
                        queue.add(v5);
                    }
                }
            }

            Pair v6 = new Pair(u.first-N, u.second+M);
            if(!(v6.first < 0 || v6.first >= R || v6.second < 0 || v6.second >= C)){
                if(!water[v6.first][v6.second]){
                    all.add(v6);
                    if(!visited[v6.first][v6.second]){
                        visited[v6.first][v6.second] = true;
                        queue.add(v6);
                    }
                }

            }

            Pair v7 = new Pair(u.first+N, u.second-M);
            if(!(v7.first < 0 || v7.first >= R || v7.second < 0 || v7.second >= C)){
                if(!water[v7.first][v7.second]){
                    all.add(v7);
                    if(!visited[v7.first][v7.second]){
                        visited[v7.first][v7.second]= true;
                        queue.add(v7);
                    }
                }

            }

            Pair v8 = new Pair(u.first-N, u.second-M);
            if(!(v8.first < 0 || v8.first >= R || v8.second < 0 || v8.second >= C)){
                if(!water[v8.first][v8.second]){
                    all.add(v8);
                    if(!visited[v8.first][v8.second] ) {
                        visited[v8.first][v8.second] = true;
                        queue.add(v8);
                    }
                }

            }

            reachNo[u.first][u.second] = all.size();
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nt = sc.nextInt();

        for(int i=0; i<nt; ++i){
            if(!sc.hasNext()) break;
            R = sc.nextInt();
            C = sc.nextInt();
            M = sc.nextInt();
            N = sc.nextInt();
            sc.nextLine();

            int wt = sc.nextInt();
            sc.nextLine();
            water = new boolean[R][C];
            for(int aa=0; aa<R; ++aa)
                Arrays.fill(water[aa], false);

            for(int j=0; j<wt; ++j){
                int wx = sc.nextInt();
                int wy = sc.nextInt();
                sc.nextLine();

                water[wx][wy] = true;
            }


            bfs(new Pair(0, 0));

            int odd, even;
            odd = 0; even=0;
            for(int p=0; p<R; ++p){
                for(int q=0; q<C; ++q){
                    if(visited[p][q]) {
                        if(reachNo[p][q] % 2 == 0) even++;
                        else odd++;

                    }
                    //System.out.print(reachNo[p][q] + " ");
                }
                //System.out.println();
            }


            System.out.format("Case %d: %d %d\n", i+1, even, odd);

        }
    }
}
