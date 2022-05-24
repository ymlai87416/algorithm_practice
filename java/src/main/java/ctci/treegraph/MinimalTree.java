package ctci.treegraph;

public class MinimalTree {

    public TreeNode minimalTree(int[] arr){
        return createMinTree(arr, 0, arr.length-1);
    }

    private TreeNode createMinTree(int[] arr, int left, int right){
        if(left == right) return new TreeNode(arr[left]);
        if(left > right) return null;
        int mid = (left + right)/2;

        TreeNode root = new TreeNode();
        root.val = arr[mid];

        root.left = createMinTree(arr, left, mid-1);
        root.right = createMinTree(arr, mid+1, right);
        return root;
    }

    public static void main(String[] args) {
        int[] data = new int[]{1,2,3,4,5,6};

        MinimalTree test = new MinimalTree();
        TreeNode tn = test.minimalTree(data);

        System.out.println(tn.toString());
    }
}
