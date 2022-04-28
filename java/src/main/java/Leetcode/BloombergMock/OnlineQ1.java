package Leetcode.BloombergMock;

public class OnlineQ1 {
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

    public int sumOfLeftLeaves(TreeNode root) {
        Object[] t = helper(root);

        if((boolean)t[0])
            return 0;

        return (int)t[1];
    }

    private Object[] helper(TreeNode node){
        if(node.left == null && node.right == null)
            return new Object[]{true, node.val};

        Object[] left = node.left == null ? null : helper(node.left);
        Object[] right = node.right == null ? null:  helper(node.right);

        int ans = 0;
        if(left!= null){
            boolean isLeaf = (boolean)left[0];
            ans += (int)left[1];
        }
        if(right != null){
            boolean isLeaf = (boolean)right[0];
            if(!isLeaf)
                ans += (int)right[1];
        }

        return new Object[]{false, ans};
    }

    public static void main(String[] args){

    }
}
