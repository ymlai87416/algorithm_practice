package Leetcode.AppleMock;

public class OnsiteQ2 {

    static class TreeNode {
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

    public TreeNode deleteNode(TreeNode root, int key) {
        //if this i the leave, just remove it,
        //if this is in the middle, then i take out the right branch and put it on the left tree,
        //and it is going to place at the right ptr of the rightmost child
        return helper(root, key);
    }

    private TreeNode helper(TreeNode root, int key){
        if(root == null) return null;
        if(root.val == key){
            if(root.left == null && root.right == null) return null;

            if(root.left == null && root.right != null) return root.right;
            if(root.right == null && root.left != null) return root.left;

            //put the right tree at the left tree and return the left tree
            TreeNode leftTreeRightMost = root.left;
            while(leftTreeRightMost.right != null)
                leftTreeRightMost = leftTreeRightMost.right;
            leftTreeRightMost.right = root.right;

            return root.left;
        }
        else if(root.val > key){
            root.left = helper(root.left, key);
            return root;
        }
        else {
            root.right = helper(root.right, key);
            return root;
        }
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        OnsiteQ2 a = new OnsiteQ2();
        TreeNode x = a.deleteNode(root, 3);

        System.out.println(x);
    }
}
