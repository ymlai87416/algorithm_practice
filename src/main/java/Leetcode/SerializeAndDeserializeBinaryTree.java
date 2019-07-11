package Leetcode;

import java.util.*;

public class SerializeAndDeserializeBinaryTree{

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        Codec  cc = new Codec();

        String a = cc.serialize(root);
        System.out.println(a);
        TreeNode b = cc.deserialize(a);
        String c = cc.serialize(b);

        System.out.println(c);
        System.out.format("Same? : %s\n", String.valueOf(a.compareTo(c)==0));
    }

    static
    public class Codec {

        private int treeDepth(TreeNode root){
            if(root == null) return 0;
            else if(root.left == null && root.right == null) return 1;
            else return Math.max(treeDepth(root.left), treeDepth(root.right))+1;
        }

        //not working for very deep tree because this is a complete binary tree...
        private void writeToListBinaryHeap(TreeNode n, Integer[] ts, int i){
            if(n == null) return;
            ts[i] = n.val;
            writeToListBinaryHeap(n.left, ts, 2*i+1);
            writeToListBinaryHeap(n.right, ts, 2*i+2);
        }

        private TreeNode readFromListBinaryHeap(Integer[] ts, int i){
            if( ts.length <= i || ts[i] == null) return null;
            TreeNode n = new TreeNode(ts[i]);
            if(2*i+1 < ts.length)
                n.left = readFromListBinaryHeap(ts, 2*i+1);
            if(2*i+2 < ts.length)
                n.right = readFromListBinaryHeap(ts, 2*i+2);
            return n;
        }

        private String writeToList(TreeNode n){
            if(n == null) return "[]";
            Queue<TreeNode> tq = new LinkedList<TreeNode>();
            tq.offer(n);
            StringBuilder sb= new StringBuilder();
            sb.append('[');
            while(!tq.isEmpty()){
                TreeNode u = tq.poll();

                if(u == null)
                    sb.append("null");
                else {
                    sb.append(u.val);
                    tq.offer(u.left);
                    tq.offer(u.right);
                }
                sb.append(',');
            }
            sb.setCharAt(sb.length()-1, ']');

            return sb.toString();
        }

        private TreeNode readFromList(Integer[] ts){
            if(ts.length == 0) return null;

            Queue<TreeNode> tq = new ArrayDeque<>();
            int ptr = 1;
            TreeNode root = new TreeNode(ts[0]);
            tq.offer(root);
            while(!tq.isEmpty()){
                TreeNode u = tq.poll();

                u.left = ts[ptr] == null ? null: new TreeNode(ts[ptr]);
                ptr++;
                u.right = ts[ptr] == null ? null : new TreeNode(ts[ptr]);
                ptr++;

                if(u.left != null) tq.offer(u.left);
                if(u.right != null) tq.offer(u.right);
            }

            return root;
        }

        private static Integer[] fromString(String string) {
            if(string.compareTo("[]") == 0) return new Integer[0];
            String[] strings = string.replace("[", "").replace("]", "").split(",");
            Integer result[] = new Integer[strings.length];
            for (int i = 0; i < result.length; i++) {
                if(strings[i].compareTo("null") != 0)
                    result[i] = Integer.parseInt(strings[i]);
            }
            return result;
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            return writeToList(root);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Integer[] intList = fromString(data);

            //return readFromList(intList, 0);
            return readFromList(intList);
        }


    }

    static
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}


