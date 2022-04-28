package Leetcode.UberMock;

import java.util.TreeMap;

public class OnlineQ2 {

    TreeMap<Integer,Integer>[] rowTm = new TreeMap[500];
    TreeMap<Integer,Integer>[] colTm = new TreeMap[500];

    public int maxKilledEnemies(char[][] grid) {
        //we can loop for 500x500 = 250000 case and see which is maximum
        int row = grid.length;
        int col = grid[0].length;

        //O(3n^2)

        for(int i=0; i<row; ++i){
            rowTm[i] = new TreeMap<Integer, Integer>();
            //for each row, count from top to bottom
            int kill = 0;
            for (int j = 0; j < col; j++) {
                if(grid[i][j] == 'W'){
                    rowTm[i].put(j, kill);
                    kill = 0;
                }
                else if(grid[i][j] == 'E')
                    kill++;
            }
            rowTm[i].put(col, kill);
        }

        for (int i = 0; i < col; i++) {
            colTm[i] = new TreeMap<Integer, Integer>();
            int kill = 0;
            //for each row, count from top to bottom
            for (int j = 0; j < row; j++) {
                if(grid[j][i] == 'W'){
                    colTm[i].put(j, kill);
                    kill = 0;
                }
                else if(grid[j][i] == 'E')
                    kill++;
            }
            colTm[i].put(row, kill);
        }

        int curMax = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(grid[i][j] == '0'){
                    int rowKill = rowTm[i].ceilingEntry(j).getValue();
                    int colKill = colTm[j].ceilingEntry(i).getValue();
                    int totalKill = rowKill+ colKill;
                    if(totalKill > curMax)
                        curMax = totalKill;
                }
            }
        }

        return curMax;
    }

    public static void main(String[] args){

    }

}
