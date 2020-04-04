package Leetcode;

import java.util.ArrayList;
import java.util.List;

/*
number: 236
url: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
level: medium
solution: 1. findPath which get a list of treenode to reach p and q, find the last common element
            2. use tree approach to find both p and q under the root. many boundary case, be careful.
 */

public class LowestCommonAncestorOfABinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        Solution sol = new Solution();
        TreeNode r = sol.lowestCommonAncestor(root, root.left, root.left.right.right);
        System.out.println(r.val);
    }

    static
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            //return findAncestor(root, p, q, new boolean[2]);
            return helper2(root, p, q);
        }


        private TreeNode helper2(TreeNode root, TreeNode p, TreeNode q){
            if(root == null) return null;

            TreeNode a = helper2(root.left, p, q);
            TreeNode b = helper2(root.right, p, q);

            if(root.val == p.val){
                if(a == q || b == q)
                    return root;
                else
                    return p;
            }
            if(root.val == q.val){
                if(a == p || b == p)
                    return root;
                else
                    return q;
            }

            if((a == p && b == q) || (a==q && b==p)){
                return root;
            }
            else if(a == p || b == p)
                return p;
            else if(a == q || b == q)
                return q;
            else if(a == null && b == null)
                return null;
            else
                return a == null ? b : a;
        }

        private TreeNode findAncestor(TreeNode root, TreeNode p, TreeNode q, boolean[] b){
            if(root == null){
                return null;
            }
            else{
                boolean[] t = new boolean[2];
                boolean[] t2=  new boolean[2];

                if(root.val == p.val || b[0]){
                    t[0] = t2[0] =true;
                }

                if(root.val == q.val || b[1]){
                    t[1] = t2[1] =true;
                }

                TreeNode pn = findAncestor(root.left, p, q, t);
                if(pn != null) return pn;
                TreeNode qn = findAncestor(root.right, p, q, t2);
                if(qn != null) return qn;

                if((t[0] || t2[0]) && (t[1] || t2[1]))
                    return root;
                else
                    return null;
            }
        }

        private TreeNode helper(TreeNode root, TreeNode p, TreeNode q){
            List<TreeNode> pPath = new ArrayList<TreeNode>();
            List<TreeNode> qPath = new ArrayList<TreeNode>();

            //get the path to p
            findPath(root, p, pPath);
            //get the path to q
            findPath(root, q, qPath);
            //find the common element
            for(int i=pPath.size()-1, j=qPath.size()-1; i>=0 && j>=0; --i, --j){
                if(pPath.get(i) != qPath.get(j))
                    return pPath.get(i+1);
            }
            return pPath.size() > qPath.size() ? qPath.get(0): pPath.get(0);
        }

        private boolean findPath(TreeNode root, TreeNode x, List<TreeNode> path){
            if(root == null) return false;
            else if(root.val == x.val) {
                path.add(root);
                return true;
            }
            else{
                if(findPath(root.left, x, path) || findPath(root.right, x, path)){
                    path.add(root);
                    return true;
                }
                else
                    return false;
            }
        }
    }

    static
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
