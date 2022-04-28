package Leetcode.FacebookMock;

public class OnsiteQ2 {

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class Solution {
        public TreeNode lcaDeepestLeaves(TreeNode root) {
            Object[] r =  helper(root, 0);
            return (TreeNode)r[1];
        }

        public Object[] helper(TreeNode root, int curLevel){
            if(root.left == null && root.right == null)
                return new Object[]{curLevel, root};

            Object[] ol = null, or= null;
            if(root.left != null)
                ol = helper(root.left, curLevel+1);
            if(root.right != null)
                or = helper(root.right, curLevel+1);

            int leftDepth = ol != null ? (int)ol[0]: 0;
            int rightDepth = or != null ? (int)or[0] : 0;
            int myDepth = Math.max(leftDepth, rightDepth);

            if(myDepth == leftDepth && leftDepth == rightDepth){
                //i am the new LCA
                System.out.println(leftDepth + " " + rightDepth + " " + root.val);
                return new Object[]{myDepth, root};
            }
            else if(myDepth == leftDepth) {
                if(ol[1] == null) ol[1] = root;
                return ol;
            }
            else{
                if(or[1] == null) or[1] = root;
                return or;
            }

        }
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        Solution s  = new Solution();
        System.out.println(s.lcaDeepestLeaves(root).val);
    }
}
