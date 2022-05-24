package ctci.recursion;
import java.util.*;

public class PaintFill {
    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, 1, -1};
    public void paintFill(int[][] image, int x, int y, int color){
        //assume color is 32 bit of ARGB
        int m = image.length;
        int n = image[0].length;

        Queue<int[]> q = new ArrayDeque<>();
        int originalColor = image[x][y];
        image[x][y] = color;

        while(!q.isEmpty()){
            int[] u = q.poll();
            int ux = u[0]; int uy = u[1];
            for(int i=0; i<4; ++i){
                int nx = ux+dx[i];
                int ny = uy+dy[i];

                if(0 <= nx && nx < m && 0 < ny && ny <=n
                        && image[nx][ny] == originalColor){
                    image[nx][ny] = color;
                    q.offer(new int[]{nx, ny});
                }
            }

        }
    }


    public static void main(String[] args) {
        //skip testing
    }
}
