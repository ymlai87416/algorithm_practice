package Leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleII {
    public static void main(String[] args){
        int numCourses = 2;
        int[][] prerequisites = {
                {0, 1}
        };

        Solution s = new Solution();
        System.out.println(s.findOrder(numCourses, prerequisites));
    }


    static
    class Solution {

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            //return helper1(numCourses, prerequisites);
            return bfsHelper(numCourses, prerequisites);
        }

        //O(n^2) solution
        public int[] helper1(int numCourses, int[][] prerequisites) {
            List<List<Integer>> adjList  = new ArrayList<>();
            boolean[] needPre = new boolean[numCourses];
            boolean[] visited = new boolean[numCourses];
            for(int i=0; i<numCourses; ++i){
                adjList.add(new ArrayList<>());
                needPre[i] = false;
                visited[i] = false;
            }

            //must fulfill all the requirement before go to next.
            for(int i=0; i<prerequisites.length; ++i) {
                int u = prerequisites[i][1];
                int v = prerequisites[i][0];
                adjList.get(v).add(u);
                needPre[u] = true;
            }

            int[] r= new int[numCourses];
            int rptr = 0;

            while(true) {
                boolean changed = false;
                for (int i = 0; i < numCourses; ++i) {
                    if(!visited[i]) {
                        boolean allFF = true;
                        for (int j = 0; j < adjList.get(i).size(); ++j) {
                            int u = adjList.get(i).get(j);
                            allFF = allFF && visited[u];
                        }
                        if (allFF) {
                            visited[i] = true;
                            changed = true;
                            r[rptr++] = i;
                        }
                    }
                }

                if(!changed)
                    break;
            }

            for(int i=0; i<numCourses; ++i)
                if(!visited[i])
                    return new int[0];

            return r;
        }

        //BFS topology sort
        private int[] bfsHelper(int numCourses, int[][] prerequisites) {
            List<List<Integer>> adjList = new ArrayList<>();
            boolean[] visited = new boolean[numCourses];
            int[] prerequisiteCount = new int[numCourses];
            for (int i = 0; i < numCourses; ++i) {
                adjList.add(new ArrayList<>());
                visited[i] = false;
            }

            //must fulfill all the requirement before go to next.
            for (int i = 0; i < prerequisites.length; ++i) {
                int u = prerequisites[i][1];
                int v = prerequisites[i][0];
                adjList.get(u).add(v);
                prerequisiteCount[v] ++;
            }

            ArrayList<Integer> ar = new ArrayList<Integer>();

            //bfs
            Queue<Integer> queue = new ArrayDeque<>();
            for(int i=0; i<numCourses; ++i) {
                if (prerequisiteCount[i] == 0)
                    queue.offer(i);
            }

            while(!queue.isEmpty()){
                int u = queue.poll();
                visited[u] = true;
                ar.add(u);

                for(int v : adjList.get(u)){
                    prerequisiteCount[v]--;
                    if(!visited[v] && prerequisiteCount[v] == 0)
                        queue.offer(v);
                }
            }

            if(ar.size() != numCourses)
                return new int[0];
            else {
                int[] result = new int[numCourses];
                for (int i = 0; i < numCourses; ++i)
                    result[i] = ar.get(i);

                return result;
            }
        }
    }
}
