package Leetcode;

public class SymmetricTree {
    public static void main(String[] args){
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        t.right = new TreeNode(2);
        t.left.left = new TreeNode(3);
        t.left.right = new TreeNode(4);
        t.right.left = new TreeNode(4);
        t.right.right = new TreeNode(3);

        Solution s = new Solution();
        System.out.println(s.isSymmetric(t));
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
        public boolean isSymmetric(TreeNode root) {
            if(root == null) return true;
            return isMirror(root.left, root.right);
        }

        private boolean isMirror(TreeNode left, TreeNode right){
            //base case, it is node
            if(left == null && right == null)
                return true;
            else if((left == null && right != null) || (left != null && right == null))
                return false;
            else if(left.val != right.val)
                return false;
            else{
                return isMirror(left.left, right.right) && isMirror(left.right, right.left);
            }
        }
    }
}
