package Leetcode;

import java.util.Arrays;

public class RobotRoomCleaner {

    static class Robot{
        int[][] room;
        int m;
        int n;
        int x; int y;
        int orientation = 0;
        int[][] diff = new int[][]{ {0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        public Robot(int[][] room, int x, int y){
            this.room = room;
            this.m = room.length;
            this.n = room[0].length;
            this.x = x;
            this.y = y;
            this.orientation = 0;
        }

        public boolean move(){
            int nx = x+diff[orientation][0];
            int ny = y+diff[orientation][1];

            if( (0<= nx && nx < m) && (0<=ny && ny < n)){
                if(room[nx][ny] != 0) {
                    x = nx;
                    y = ny;
                    return true;
                }
            }
            return false;
        }

        public void turnLeft(){
            this.orientation--;
            if(this.orientation < 0) this.orientation +=4;
        }
        public void turnRight(){
            this.orientation++;
            if(this.orientation >=4 ) this.orientation-=4;
        }

        public void clean(){
            room[x][y] = 8;

            System.out.println("Robot at: " + x + " " + y);
            for(int i=0; i<m; ++i){
                for(int j=0; j<n; ++j)
                    System.out.print(room[i][j]);
                System.out.println();
            }
        }
    }

    public static void main(String[] args){
        RobotRoomCleaner cc = new RobotRoomCleaner();
        int[][] room = {{1,1,1,1,1,0,1,1},{1,1,1,1,1,0,1,1},{1,0,1,1,1,1,1,1},{0,0,0,1,0,0,0,0},{1,1,1,1,1,1,1,1}};
        Robot rr = new Robot(room, 1, 3);
        cc.cleanRoom(rr);
    }

    Robot robot;


    int[][] map = new int[201][401];
    int[][] diff = new int[][]{ {0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    public void cleanRoom(Robot robot) {
        //each cell have 2 stage, visited, explored, for not visited not yet created.
        //try dfs
        this.robot = robot;
        for(int i=0; i<201; ++i)
            Arrays.fill(map[i], -1);

        dfs(100, 200, 0);
    }

    public void dfs(int x, int y, int direction){
        System.out.println("D" + x + " " +  y + " " + direction);
        robot.clean();
        map[x][y] = 0;

        for(int i=0; i<4; ++i){
            int nx = x + diff[direction][0];
            int ny = y + diff[direction][1];
            if(map[nx][ny] == -1){  //not visited, no need to have visited
                robot.move();
                dfs(x, y, direction);
            }
            //now turn left
            robot.turnLeft();
            direction +=1;
        }


    }


}


