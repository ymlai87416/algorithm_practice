package ctci.treegraph;

public class FirstCommonAncestor {


    public TreeNode firstCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return null;
        if(root == p || root == q) return root;

        TreeNode left = firstCommonAncestor(root.left, p, q);
        TreeNode right = firstCommonAncestor(root.right, p, q);

        if ((left == p && right == q) || ((left == q) && (right == p) )) return root;
        if (left == null  && right == null) return null;
        if (left == null) return right;
        if (right == null) return left;

        return null;
    }


    public static void main(String[] args) {
        FirstCommonAncestor test = new FirstCommonAncestor();

        TreeNode r1 = Utility.createTree(new Integer[]{20, 10, 30, 5, 15, null, null, 3, 7, null, 17,
            null, null, null, null, null, null});
        TreeNode p = Utility.findNode(r1, 7);
        TreeNode q = Utility.findNode(r1, 17);

        System.out.println(test.firstCommonAncestor(r1, p, q));


        TreeNode r2 = Utility.createTree(new Integer[]{3, 1, 5, null, null, null, 8,null, null});
        p = Utility.findNode(r1, 5);
        q = Utility.findNode(r1, 7);

        System.out.println(test.firstCommonAncestor(r1, p, q));
    }
}
