package GoogleCodeJam.Y2011.Round1B.C;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2011\\Round1B\\C\\C-test.in";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            out = new PrintStream(System.out);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    boolean debugflag = false;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    private ArrayList<ArrayList<Integer>> getRooms(int N, int M, int[] U, int[] V){

        ArrayList<ArrayList<Integer>> rooms = new ArrayList<>();

        ArrayList<Integer> mainRoom = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            mainRoom.add(i);
        }
        rooms.add(mainRoom);

        for (int i = 0; i < M; i++) {
            rooms = splitRoom(rooms, U[i], V[i]);
        }

        return rooms;
    }

    private void solveBig(int N, int M, int[] U, int[] V){

        ArrayList<ArrayList<Integer>> rooms = getRooms(N, M, U, V);

        //a wall can only cut a room into half, so N-3 wall cut the room into N-2 section
        ArrayList<Integer>[] vertexRoom = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            vertexRoom[i] = new ArrayList<Integer>();
        }

        int minC = Integer.MAX_VALUE;
        int minRoomIdx = -1;
        for (int i = 0; i < rooms.size(); i++) {
            ArrayList<Integer> curRoom = rooms.get(i);
            if(curRoom.size() < minC) {
                minC = rooms.get(i).size();
                minRoomIdx = i;
            }

            for (int j = 0; j < curRoom.size(); j++) {
                vertexRoom[curRoom.get(j)].add(i);
            }
        }

        //find the minimum room and assign it with color
        //and then find the room next to it, which have the least pillar to assign and assign it
        //add as pirority queue or what
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            result[i] = -1;
        }
        boolean[] visited = new boolean[rooms.size()];
        for (int i = 0; i < rooms.size(); i++) {
            visited[i] = false;
        }

        PriorityQueue<Room> pq = new PriorityQueue<>();
        pq.offer(new Room(minRoomIdx, minC));

        Random random = new Random();
        HashMap<Integer, Room> pqRoom = new HashMap<>();

        while(!pq.isEmpty()){
            Room r = pq.poll();
            //debug("coloring room" + r.room + " " + r.colorLeft);
            ArrayList<Integer> curRoom = rooms.get(r.room);
            visited[r.room] =true;

            int[] color = new int[minC];
            for (int i = 0; i < curRoom.size(); i++) {
                int cc = result[curRoom.get(i)];
                if(cc != -1) color[cc]++;
            }
            //find a color for each unassigned vertex
            Stack<Integer> needColor = new Stack<>();
            for (int i = 0; i < minC; i++) {
                if(color[i] == 0) needColor.push(i);
            }

            HashSet<Integer> pendingUpdateRooms = new HashSet<>();
            for (int i = 0; i < curRoom.size(); i++) {
                if(result[curRoom.get(i)] == -1){
                    if(!needColor.isEmpty()){
                        result[curRoom.get(i)] = needColor.pop();
                    }
                    else{
                        //find adjacency
                        int fcolor1, fcolor2;
                        if(i-1 < 0)
                            fcolor1= result[curRoom.get(curRoom.size()-1)];
                        else
                            fcolor1 = result[curRoom.get(i-1)];

                        if(i+1 >= curRoom.size())
                            fcolor2 = result[curRoom.get(0)];
                        else
                            fcolor2 = result[curRoom.get(i+1)];

                        while(true){
                            int rc =  random.nextInt(minC);
                            if(rc != fcolor1 && rc!=fcolor2) {
                                result[curRoom.get(i)] = rc;
                                break;
                            }
                        }
                    }
                }
                ArrayList<Integer> updatedRooms = vertexRoom[curRoom.get(i)];
                pendingUpdateRooms.addAll(updatedRooms);
            }

            //now this is also updated.
            for (Integer roomIdx: pendingUpdateRooms) {

                if(visited[roomIdx]) continue;

                if(pqRoom.containsKey(roomIdx)){
                    Room ur = pqRoom.get(roomIdx);
                    pq.remove(ur);
                }

                int noColor = 0;
                for (int i = 0; i < rooms.get(roomIdx).size(); i++) {
                    int vertex = rooms.get(roomIdx).get(i);

                    if(result[vertex] == -1)
                        ++noColor;
                }
                Room addR = new Room(roomIdx, noColor);
                pq.offer(addR);
                pqRoom.put(roomIdx, addR);
            }
        }

        try {
            if (!checkValid(result, rooms, minC)) {
                debug("XXXX shit");
            }
        }
        catch(Exception ex){
            debug("XXXX bigger shit");
        }

        out.println(minC);
        for (int j = 0; j < N; j++) {
            if(j==0) out.print(result[0]+1);
            else out.print(" " + (result[j]+1));
        }
        out.println();

    }

    static class Room implements Comparable<Room>{
        int room;
        int colorLeft;

        public Room(int room, int colorLeft){
            this.room = room;
            this.colorLeft = colorLeft;
        }

        @Override
        public int compareTo(Room o) {
            if(o.colorLeft == colorLeft)
                return room  - o.room;
            else
                return colorLeft - o.colorLeft;
        }
    }


    private void solve(int N, int M, int[] U, int[] V) {

        ArrayList<ArrayList<Integer>> rooms = getRooms(N, M, U, V);

        //the maximum number of catnip is the minimum pillar in a room
        //1 set of edge will split the room
        int minC = Integer.MAX_VALUE;
        for (int i = 0; i < rooms.size(); i++) {
            if(rooms.get(i).size() < minC)
                minC =rooms.get(i).size();
        }


        /*
        StringBuilder sb;
        for (int i = 0; i < rooms.size(); i++) {
            sb = new StringBuilder();
            for (int j = 0; j < rooms.get(i).size(); j++) {
                sb.append(rooms.get(i).get(j) + " ");
            }
            debug(sb.toString());
        }*/

        //now brute force
        for (int i = 0; i < Math.pow(minC, N); i++) {
            int[] t = new int[N];
            int ci = i;
            for (int j = 0; j < N; j++) {
                t[j] = ci % minC;
                ci /= minC;
            }

            if(checkValid(t, rooms, minC)){
                out.println(minC);
                for (int j = 0; j < N; j++) {
                    if(j==0) out.print(t[0]+1);
                    else out.print(" " + (t[j]+1));
                }
                out.println();

                return;
            }
        }

    }

    private boolean checkValid(int[] color, ArrayList<ArrayList<Integer>> rooms, int C){
        int[] bb = new int[C];
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = 0; j < C; j++) {
                bb[j] = 0;
            }
            ArrayList<Integer> curRoom = rooms.get(i);
            for (int j = 0; j < curRoom.size(); j++) {
                bb[color[curRoom.get(j)]]++;
            }
            for (int j = 0; j <C; j++) {
                if(bb[j] == 0) return false;
            }
        }

        return true;
    }

    

    private ArrayList<ArrayList<Integer>> splitRoom(ArrayList<ArrayList<Integer>> rooms, int u, int v){

        ArrayList<ArrayList<Integer>> newRooms = new ArrayList<>();

        for (int i = 0; i < rooms.size(); i++) {
           ArrayList<Integer> curRoom = rooms.get(i);
           boolean split = false;
           boolean ufound = false; boolean vfound=false;
            for (int j = 0; j < curRoom.size(); j++) {
                if(curRoom.get(j) == u) ufound= true;
                if(curRoom.get(j) == v) vfound= true;
            }

            split = ufound && vfound;

            if(!split)
                newRooms.add(curRoom);
            else{
                ArrayList<Integer> room1 = new ArrayList<Integer>();
                ArrayList<Integer> room2 = new ArrayList<Integer>();
                boolean start = false;
                for (int j = 0; j < curRoom.size(); j++) {

                    if(!start &&curRoom.get(j) == u){
                        start = true;
                        room1.add(u);
                        room1.add(v);
                    }

                    if(start){
                        room2.add(curRoom.get(j));
                    }
                    else
                        room1.add(curRoom.get(j));

                    if(start && curRoom.get(j) == v)
                        start = false;

                }

                newRooms.add(room1);
                newRooms.add(room2);
            }
        }


        return newRooms;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[] U = new int[M];
            int[] V = new int[M];

            for (int j = 0; j < M; j++) {
                U[j] = sc.nextInt()-1;
            }

            for (int j = 0; j < M; j++) {
                V[j] = sc.nextInt()-1;
            }
            solveBig(N, M, U, V);

        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
