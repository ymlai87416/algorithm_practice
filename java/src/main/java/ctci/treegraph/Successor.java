package ctci.treegraph;

public class Successor {

    public TreeNode successor(TreeNode node){
        if(node.right != null) return node.right;

        //else go to parent,
        // if it is from left tree. return parent node
        //if it is from right tree, go up again

        while(node != null){
            TreeNode parent = node.parent;
            if(parent == null) return null;
            if(parent.left == node)
                return parent;
            else{
                node = parent;
            }
        }

        return null; //this is the last element of the tree.
    }

    public static void main(String[] args) {
        TreeNode t20 = new TreeNode(20);
        TreeNode t10 = new TreeNode(10);
        TreeNode t30 = new TreeNode(30);
        TreeNode t25 = new TreeNode(25);
        t20.left = t10; t20.right = t30;
        t10.parent = t20;t30.parent = t20;
        t10.right=t25;
        t25.parent=t10;

        Successor test = new Successor();
        System.out.println(test.successor(t20).val);
        System.out.println(test.successor(t10).val);
        System.out.println(test.successor(t30));
        System.out.println(test.successor(t25).val);
    }

}
