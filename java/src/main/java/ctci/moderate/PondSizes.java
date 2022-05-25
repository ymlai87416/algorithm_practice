package ctci.moderate;
import java.util.*;

public class PondSizes {
    int[] dx = new int[]{-1, 1, 0, 0, -1, -1, 1, 1};
    int[] dy = new int[]{0, 0, -1, 1, 1, -1, 1, -1};
    public List<Integer> pondSize(int[][] matrix ){
        int m = matrix.length;
        int n = matrix[0].length;

        UnionFind uf = new UnionFind(m*n);

        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(matrix[i][j] != 0) continue;

                int index = i * n+j;

                //up, down, left, right
                for(int p=0; p<8; ++p){
                    int nx = i + dx[p];
                    int ny = j + dy[p];

                    if(0 <= nx && nx < m && 0 <= ny && ny < n){
                        if(matrix[nx][ny] != 0) continue;

                        int index2 = nx * n + ny;

                        if(uf.findSet(index) != uf.findSet(index2)){
                            uf.unionSet(index, index2);
                        }
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<Integer>();

        //now we union all set
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(matrix[i][j] == 0 && uf.findSet(i*n+j) == i*n+j)
                result.add(uf.getSize(i*n+j));
            }
        }

        return result;
    }


    public static void main(String[] args) {
        int[][] data = new int[][]{
                {0, 2, 1, 0},
                {0, 1, 0, 1},
                {1, 1, 0, 1},
                {0, 1, 0, 1}
        };

        PondSizes test = new PondSizes();
        System.out.println(test.pondSize(data));
    }
}
