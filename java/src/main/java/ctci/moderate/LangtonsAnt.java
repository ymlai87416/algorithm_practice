package ctci.moderate;
import java.util.*;

public class LangtonsAnt {
    int[] dx = new int[]{0, 1, 0, -1};
    int[] dy = new int[]{1, 0, -1, 0};
    public void printKMoves(int K){
        //assume all the grid is white
        HashSet<Position> black = new HashSet<>();
        int ax = 0;
        int ay = 0;
        int aor = 0;

        for(int i=0; i<K; ++i){
            Position p = new Position(ax, ay);
            System.out.printf("Current at x: %d, y: %d\n", ax, ay);
            if(black.contains(p)){
                black.remove(p);
                aor = (aor-1+4) % 4;
            }
            else{
                black.add(p);
                aor = (aor+1) % 4;
            }
            ax = ax+dx[aor];
            ay = ay+dy[aor];
        }

        System.out.printf("Final at x: %d, y: %d\n", ax, ay);
    }

    public static void main(String[] args) {
        LangtonsAnt test = new LangtonsAnt();
        test.printKMoves(10);
    }
}

class Position{
    int  x;
    int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object obj){
        if(obj instanceof Position){
            Position op = (Position)obj;
            return op.x == x && op.y == y;
        }
        else return false;
    }

    public int hashCode(){
        return (x * 1_000_000_007 + y) % 1_000_000_007;
    }
}

