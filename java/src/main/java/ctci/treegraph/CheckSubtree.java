package ctci.treegraph;

public class CheckSubtree {
    public boolean checkSubtree(TreeNode t1, TreeNode t2){
        return helper(t1, t2);
    }

    private boolean helper(TreeNode t1, TreeNode t2){

        if(t1 == null && t2 == null) return true;
        if(t1 == null) return false;
        if(t2 == null) return false;

        if(t1.val == t2.val){
            boolean l = helper(t1.left, t2.left);
            boolean r = helper(t1.right, t2.right);
            if (l && r) return true;
        }

        boolean l = helper(t1.left, t2);
        if(l == true) return true;
        boolean r = helper(t1.right, t2);
        return r;
    }

    public static void main(String[] args) {
        CheckSubtree test = new CheckSubtree();

        TreeNode t1 = Utility.createTree(new Integer[]{1, 2, 3, 4, null, null,null, null, null});
        TreeNode t2 = Utility.createTree(new Integer[]{3, null, null});
        TreeNode t3 = Utility.createTree(new Integer[]{2, 4, null, null, null});

        System.out.println(test.checkSubtree(t1, t2));
        System.out.println(test.checkSubtree(t1, t3));

        System.out.println(test.checkSubtree(t2, t3));
    }
}
