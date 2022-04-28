package Leetcode.BloombergMock;

public class PhoneQ2 {

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

    int cnt;
    public int countUnivalSubtrees(TreeNode root) {
        cnt = 0;
        if(root == null) return 0;

        helper(root);

        return cnt;
    }

    private int[] helper(TreeNode n){
        if(n.left == null && n.right == null) {
            cnt +=1;
            return new int[]{n.val, n.val};
        }

        int[] left = n.left == null ? null : helper(n.left);
        int[] right = n.right == null ? null : helper(n.right);

        int min=n.val, max=n.val;
        if(left != null){
            min = Math.min(min, left[0]);
            max = Math.max(max, left[1]);
        }
        if(right != null){
            min = Math.min(min, right[0]);
            max = Math.max(max, right[1]);
        }

        if(min == max)
            ++cnt;

        return new int[]{min, max};
    }

    public static void main(String[] args){

    }
}
