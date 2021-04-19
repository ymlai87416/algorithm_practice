package Leetcode;

/**
number: 98
url: https://leetcode.com/problems/validate-binary-search-tree/
level: medium
solution: check the tree using recursion done

#tree #dfs #recursion

 **/

public class ValidateBinarySearchTree {
    public static void main(String[] args) {
        TreeNode t = new TreeNode(5);
        t.left = new TreeNode(1);
        t.right = new TreeNode(4);
        t.right.left = new TreeNode(3);
        t.right.right=  new TreeNode(6);
        Solution s = new Solution();
        System.out.println(s.isValidBST(t));
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

    static
    class Solution {
        public boolean isValidBST(TreeNode root){
            return isValidBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        public boolean isValidBSTHelper(TreeNode root, int min, int max) {
            if(root == null) return true;
            if (!(min <= root.val && root.val <= max)) return false;
            if(root.left != null && root.val <= root.left.val)
                return false;
            if(root.right != null && root.val >= root.right.val)
                return false ;
            return isValidBSTHelper(root.left, min, root.val-1) && isValidBSTHelper(root.right, root.val+1, max);
        }
    }
}
