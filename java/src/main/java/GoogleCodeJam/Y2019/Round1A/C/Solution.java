package GoogleCodeJam.Y2019.Round1A.C;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            /*
            FILENAME = "Solution-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
                    */

            //IN = "A-test.in";
            IN = null;
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



    char[][] edge = new char[401][401];
    boolean[] visited = new boolean[401];
    private void solve(int r, int c){

        int[] path = null;
        HamiltonianPath hp = new HamiltonianPath(r, c);
        for(int i=0; i<r; ++i){
            for(int j=0; j<c; ++j){
                path = hp.findPath(i, j);
                if(path!= null){
                    break;
                }
            }
        }

        if(path==null)
            out.println("IMPOSSIBLE");
        else{
            out.println("POSSIBLE");
            for(int i=0; i<path.length; ++i){
                int x = path[i] / c + 1;
                int y = path[i] % c + 1;

                out.format("%d %d\n", x, y);
            }
        }
    }

    private boolean checkConnectedGraph(int r, int c) {
        for(int i=0; i<401; ++i) visited[i] = false;

        Stack<Integer> st = new Stack<Integer>();
        visited[0]  =true;
        st.push(0);

        while(!st.empty()){
            int u = st.pop();
            for(int v=0; v<r*c; ++v){
                if(edge[u][v] == 1) {
                    if (!visited[v]) {
                        visited[v] = true;
                        st.add(v);
                    }
                }
            }
        }

        for(int i=0; i<401; ++i){
            if (!visited[i]) return false;
        }

        return true;
    }

    private void findHamiltonian(){
    }

    private void run() throws Exception {
        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            int r = sc.nextInt();
            int c = sc.nextInt();

            solve(r, c);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}

class HamiltonianPath {
    private final int r;
    private final int c;
    private final int vertexCount;
    private final boolean[][] adjacencyMatrix;

    private boolean[] visited;

    public HamiltonianPath(int r, int c) {
        this.r = r;
        this.c = c;
        this.vertexCount = r * c;
        this.adjacencyMatrix = generateAdjacencyMatrix();
    }

    public static void printPath(int boardSize, int path[]) {
        for (int i : path) {
            int x = i % boardSize;
            int y = i / boardSize;
            System.out.print("(" + x + "," + y + ") ");
        }
        System.out.println();
    }

    private boolean solver(int[] path, int position) {
        // base case for recursion.. path completed if vertexCount is equal to position
        if (position == vertexCount) {
            return true;
        }

        for (int v = 0; v < vertexCount; v++) {
            // check if v can be added to path
            if (isSafe(v, path, position)) {
                // add v to path
                path[position] = v;
                visited[v] = true;

                // recursively solve for next position
                if (solver(path, position+1)) {
                    return true;
                }

                // backtrack if no vertex can be added to path after v
                path[position] = -1;
                visited[v] = false;
            }
        }

        return false;
    }

    int[] findPath(int x, int y) {
        // reset visited array
        visited = new boolean[this.vertexCount];
        int[] path = new int[vertexCount];

        Arrays.fill(path, -1);

        path[0] = positionToVertex(x, y);
        visited[path[0]] = true;

        if (solver(path, 1)) {
            return path;
        }

        return null;
    }

    private int positionToVertex(int x, int y) {
        return x * this.c + y;
    }

    private boolean isSafe(int v, int[] path, int position) {
        // safe, if two vertices are adjacent and the target is not visited..
        return adjacencyMatrix[path[position-1]][v] && !visited[v];
    }

    // generate adjacency matrix for given board size
    private boolean[][] generateAdjacencyMatrix() {
        boolean[][] adjMatrix = new boolean[vertexCount][vertexCount];

        for(int i=0; i<r*c; ++i){
            for(int j=i; j<r*c; ++j){
                int row_i = i/c;
                int col_i = i%c;
                int row_j = j/c;
                int col_j = j%c;

                if(row_i == row_j)
                    adjMatrix[i][j] = adjMatrix[j][i] = false;
                else if(col_i == col_j)
                    adjMatrix[i][j] = adjMatrix[j][i] = false;
                else if(row_i - col_i == row_j - col_j)
                    adjMatrix[i][j] = adjMatrix[j][i] = false;
                else if(row_i + col_i == row_j + col_j)
                    adjMatrix[i][j] = adjMatrix[j][i] = false;
                else
                    adjMatrix[i][j] = adjMatrix[j][i] = true;
            }
        }

        return adjMatrix;
    }
}