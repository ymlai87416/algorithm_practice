package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
url: https://leetcode.com/problems/clone-graph/
level: medium
solution: create all nodes and build the linkage between them

#dfs #bfs #graph
 */

public class CloneGraph {
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{6,9}};
        int[] newInterval = {2,5};
        Solution sol = new Solution();
        System.out.println(sol.cloneGraph(null));
    }

    static
    class Solution {
        HashMap<Node, Node> map;
        public Node cloneGraph(Node node) {
            map = new HashMap<>();

            //create all node
            helper(node);

            //build dependency
            for(Node n : map.keySet()){
                Node newNode = map.get(n);
                newNode.neighbors = new ArrayList<>();

                for(Node nr : n.neighbors){
                    newNode.neighbors.add(map.get(nr));
                }
            }

            return map.get(node);
        }

        private Node helper(Node node){
            if(map.containsKey(node))
                return node;

            Node newNode = clone(node);

            for(Node n: node.neighbors) {
                helper(n);
            }

            return newNode;
        }

        private Node clone(Node node){
            if(map.containsKey(node))
                return map.get(node);

            Node r = new Node();
            r.val = node.val;

            map.put(node, r);

            return r;
        }
    }

    static
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    };

}
