package Leetcode;

public class ShortestUnsortedContinousSubarray {
    public static void main(String[] args){
        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(3);
        tree1.right = new TreeNode(2);
        tree1.left.left = new TreeNode(5);

        TreeNode tree2 = new TreeNode(2);
        tree2.left = new TreeNode(1);
        tree2.right = new TreeNode(3);
        tree2.left.right = new TreeNode(4);
        tree2.right.right = new TreeNode(7);

        Solution s = new Solution();
        TreeNode result = s.mergeTrees(tree1, tree2);
        System.out.println(result);
    }

    static
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    static
    class Solution {
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if(t1 == null && t2 != null) return t2;
            if(t1 == null && t2 == null) return null;
            if(t1 != null && t2 == null) return t1;

            TreeNode result = new TreeNode(t1.val+t2.val);
            result.left = mergeTrees(t1.left, t2.left);
            result.right = mergeTrees(t1.right, t2.right);
            return result;
        }
    }
}
