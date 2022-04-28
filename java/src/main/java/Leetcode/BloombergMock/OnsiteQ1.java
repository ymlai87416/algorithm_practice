package Leetcode.BloombergMock;

public class OnsiteQ1 {

    static  class TreeNode {
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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null && q != null) return false;
        if(p != null && q == null) return false;
        if(p.val != q.val) return false;

        boolean a = isSameTree(p.left, q.left);
        if(!a)
            return false;
        a = isSameTree(p.right, q.right);
        if(!a)
            return false;
        return true;
    }

    public static void main(String[] args){
        OnlineQ1 s = new OnlineQ1();

    }
}
