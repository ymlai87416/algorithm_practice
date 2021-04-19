package Leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


/*
problem : https://leetcode.com/problems/course-schedule/
level: medium
solution: topological sort (bfs 4ms / dfs 7ms)

#dfs #bfs #graph #topologicalSort
 */

public class CourseSchedule {
    public static void main(String[] args){
        int numCourses = 2;
        int[][] prerequisites = {
                {0, 1}
        };

        Solution s = new Solution();
        System.out.println(s.canFinish(numCourses, prerequisites));
    }


    static
    class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            return bfsHelper(numCourses, prerequisites);
        }

        private boolean bfsHelper(int numCourses, int[][] prerequisites) {
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

            /*
            while (true) {
                boolean changed = false;
                for (int i = 0; i < numCourses; ++i) {
                    if (!visited[i]) {
                        boolean allFF = true;
                        for (int j = 0; j < adjList.get(i).size(); ++j) {
                            int u = adjList.get(i).get(j);
                            allFF = allFF && visited[u];
                        }
                        if (allFF) {
                            visited[i] = true;
                            changed = true;
                        }
                    }
                }

                if (!changed)
                    break;
            }*/

            //bfs
            Queue<Integer> queue = new ArrayDeque<>();
            for(int i=0; i<numCourses; ++i) {
                if (prerequisiteCount[i] == 0)
                    queue.offer(i);
            }

            while(!queue.isEmpty()){
                int u = queue.poll();
                visited[u] = true;

                for(int v : adjList.get(u)){
                    prerequisiteCount[v]--;
                    if(!visited[v] && prerequisiteCount[v] == 0)
                        queue.offer(v);
                }
            }

            for (int i = 0; i < numCourses; ++i)
                if (!visited[i])
                    return false;

            return true;
        }

        private boolean dfsHelper(int numCourses, int[][] prerequisites) {
            List<List<Integer>> adjList = new ArrayList<>();
            boolean[] needPre = new boolean[numCourses];
            int[] visited = new int[numCourses];
            for (int i = 0; i < numCourses; ++i) {
                adjList.add(new ArrayList<>());
                needPre[i] = false;
                visited[i] = 0;
            }

            for (int i = 0; i < prerequisites.length; ++i) {
                int u = prerequisites[i][0];
                int v = prerequisites[i][1];
                adjList.get(v).add(u);
                needPre[u] = true;
            }

            for(int i=0; i<numCourses; ++i){
                if(visited[i] == 0 && isCycle(i, adjList, visited))
                    return false;
            }
            return true;
        }

        private boolean isCycle(int u, List<List<Integer>> adjList, int[] visited){
            for(Integer v: adjList.get(u)){
                if(visited[v] == 1)
                    return true;
                else if(visited[v] == 0) {
                    visited[v] = 1;
                    boolean r = isCycle(v, adjList, visited);
                    visited[v] = 0;

                    if(r) return true;
                }
            }

            visited[u] = 2;
            return false;
        }
    }
}
