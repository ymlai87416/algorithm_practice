package Leetcode.AmazonMock;

import java.util.HashMap;

//next time you have to explain what is BST before you do it. save your face.
public class OnsiteQ3 {

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

    public int maxSumBST(TreeNode root) {
        HashMap<TreeNode, Integer> bsts = new HashMap<>();

        helper(root, bsts);

        int result = 0;
        for (TreeNode tn: bsts.keySet()) {
            result = Math.max(result, bsts.get(tn));
        }

        return result;
    }

    public Object[] helper(TreeNode root, HashMap<TreeNode, Integer> bsts){
        //this function will registered itself as a BST, and also the maximum
        if(root.left == null && root.right == null) {
            bsts.put(root, root.val);
            return new Object[]{root.val, true, root.val, root.val};
        }

        Object[] left = root.left == null ? null : helper(root.left, bsts);
        Object[] right = root.right == null ? null : helper(root.right, bsts);

        boolean isBst = true;
        int treeMin = root.val;
        int treeMax = root.val;
        int treeSum = root.val;
        if(left != null){
            treeSum += (int)left[0];
            boolean leftBst = (boolean)left[1];
            int leftMin = (int)left[2];
            int leftMax = (int)left[3];

            isBst = isBst && leftBst && leftMax< root.val;
            treeMin = leftMin;
        }

        if(right != null){
            treeSum += (int)right[0];
            boolean rightBst = (boolean)right[1];
            int rightMin = (int)right[2];
            int rightMax = (int)right[3];

            isBst = isBst && rightBst && rightMin > root.val;
            treeMax = rightMax;
        }

        if(isBst)
            bsts.put(root, treeSum);

        return new Object[]{treeSum, isBst, treeMin, treeMax};
    }

    public static void main(String[] args){
        TreeNode r = new TreeNode(1);
        r.left = null; r.right = new TreeNode(10);
        r.right.left = new TreeNode(-5);
        r.right.right = new TreeNode(20);
        OnsiteQ3  s= new OnsiteQ3();
        System.out.println(s.maxSumBST(r));
    }
}
