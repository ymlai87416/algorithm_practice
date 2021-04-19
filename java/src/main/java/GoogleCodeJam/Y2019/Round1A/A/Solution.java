package GoogleCodeJam.Y2019.Round1A.A;


import java.io.File;
import java.io.PrintStream;
import java.util.*;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2019\\Round1A\\A\\A-test.in";
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

    char[][] edge = new char[401][401];
    boolean[] visited = new boolean[401];
    //this can only solve x node
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

    private void solveByCut(int r, int c){
        if(r == 2 && (c == 2 || c==3 || c==4)) {
            out.println("IMPOSSIBLE");
            return;
        }
        if(r==3 && (c==2 && c==3)){
            out.println("IMPOSSIBLE");
            return;
        }
        if(r==4 && c == 2){
            out.println("IMPOSSIBLE");
            return;
        }
        if(r==2 || r == 3 || r==4 ){
            solve(r, c);
            return;
        }

        int rowCover = 0;
        if(r%2 == 0) rowCover = r;
        else rowCover = r-3;
        
        int lastNode = -1;
        ArrayList<Integer> fullpath = new ArrayList<Integer>();
        for (int i = 0; i < rowCover; i+=2) {

            HamiltonianPath hp = new HamiltonianPath(2, c);
            int[] path = null;
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < c; k++) {
                    int curNode = (i*2+j)*c+k;
                    if(lastNode != -1 && okJump(lastNode, curNode, c) ) {
                        path = hp.findPath(j, k);
                        if (path != null) {
                            break;
                        }
                    }
                }

                if(path != null){
                    for (int k = 0; k < path.length; k++) {
                        fullpath.add(path[k] + i*2*c);
                    }
                    lastNode = path[path.length-1]+ i*2*c;
                }
            }
        }

        if(rowCover != r){
            HamiltonianPath hp = new HamiltonianPath(3, c);
            int[] path = null;
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < c; k++) {
                    int curNode = (rowCover+j)*c+k;
                    if(lastNode != -1 && okJump(lastNode, curNode, c) ) {
                        path = hp.findPath(j, k);
                        if (path != null) {
                            break;
                        }
                    }
                }

                if(path != null){
                    for (int k = 0; k < path.length; k++) {
                        fullpath.add(path[k] + rowCover*c);
                    }
                }
            }
        }

        out.println("POSSIBLE");
        for(int i=0; i<fullpath.size(); ++i){
            int x = fullpath.get(i) / c + 1;
            int y = fullpath.get(i) % c + 1;

            out.format("%d %d\n", x, y);
        }
    }

    private boolean okJump(int from, int to, int c){
        int fx = from/c+1;
        int fy = from%c +1;
        int tx = from/c+1;
        int ty = from%c+1;
        if(fx == tx || fy == ty || (fx+fy) == (tx+ty) || (fx-fy) == (tx-ty))
            return false;
        return true;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
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

        int beam = 6;
        List<Integer> shuffleV = new ArrayList<Integer>();
        for (int i = 0; i < vertexCount; i++) {
            shuffleV.add(i);
        }
        Collections.shuffle(shuffleV);
        for (int i = 0; i < vertexCount && beam > 0; i++) {
            // check if v can be added to path
            int v = shuffleV.get(i);
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
                --beam;
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